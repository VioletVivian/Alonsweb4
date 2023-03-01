
package cn.chitanda.music.media

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.media.MediaBrowserServiceCompat
import cn.chitanda.music.media.extensions.flag
import cn.chitanda.music.media.extensions.id
import cn.chitanda.music.media.extensions.toMediaItem
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MusicService"

@AndroidEntryPoint
class MusicService : MediaBrowserServiceCompat() {
    @Inject
    lateinit var musicSource: MusicSource

    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector
    private var isForegroundService = false
    private lateinit var notificationManager: MediaNotificationManager
    private var currentPlaylist: List<MediaMetadataCompat> = emptyList()
    private val audioAttributes = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()
    private val exoPlayer: ExoPlayer by lazy(mode = LazyThreadSafetyMode.NONE) {
        ExoPlayer.Builder(this).setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true).build()
            .apply { addListener(ExoPlayerEventListener()) }
    }

    override fun onCreate() {
        super.onCreate()
        val sessionPendingIntent =
            packageManager?.getLaunchIntentForPackage(packageName)?.let { intent ->
                PendingIntent.getActivity(
                    this, 0, intent, PendingIntent.FLAG_IMMUTABLE
                )
            }
        mediaSession = MediaSessionCompat(this, "MusicService")
            .apply {
                setSessionActivity(sessionPendingIntent)
                isActive = true
            }
        sessionToken = mediaSession.sessionToken
        notificationManager =
            MediaNotificationManager(this, mediaSession.sessionToken, PlayerNotificationListener())

        mediaSessionConnector = MediaSessionConnector(mediaSession).apply {
            setPlaybackPreparer(PlaybackPreparer())
            setQueueNavigator(QueueNavigator(mediaSession))
            setPlayer(exoPlayer)
        }
        notificationManager.showNotification(player = exoPlayer)
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        Log.d(TAG, "onGetRoot: ")
        return BrowserRoot(BROWSABLE_ROOT, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<List<MediaBrowserCompat.MediaItem>>
    ) {
        Log.d(TAG, "onLoadChildren: $parentId")
        musicSource.load(parentId)
        val sentResult = musicSource.whenReady { success ->
            if (success) {
                result.sendResult(musicSource.map {
                    MediaBrowserCompat.MediaItem(
                        it.description,
                        it.flag
                    )
                })
                currentPlaylist = musicSource.toList()
            } else {
                result.sendResult(null)
            }
        }
        if (!sentResult) result.detach()
    }

    private fun preparePlaylist(
        metadataList: List<MediaMetadataCompat>,
        itemToPlay: MediaMetadataCompat?,
        playWhenReady: Boolean,
        playbackStartPositionMs: Long
    ) {
        Log.d(TAG, "preparePlaylist: $itemToPlay")
        // Since the playlist was probably based on some ordering (such as tracks
        // on an album), find which window index to play first so that the song the
        // user actually wants to hear plays first.
        val initialWindowIndex = if (itemToPlay == null) 0 else metadataList.indexOf(itemToPlay)
        currentPlaylist = metadataList

        exoPlayer.playWhenReady = playWhenReady
        exoPlayer.stop()
        // Set playlist and prepare.
        exoPlayer.setMediaItems(
            metadataList.map { it.toMediaItem() }, initialWindowIndex, playbackStartPositionMs
        )
        exoPlayer.prepare()
    }

    override fun onDestroy() {
        notificationManager.hideNotification()
        super.onDestroy()
    }

    private inner class QueueNavigator(