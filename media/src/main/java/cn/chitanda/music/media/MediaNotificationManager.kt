package cn.chitanda.music.media

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import cn.chitanda.music.media.extensions.displaySubtitle
import cn.chitanda.music.media.extensions.displayTitle
import coil.imageLoader
import coil.request.ImageRequest
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

/**
 * @author: Chen
 * @createTime: 2022/1/24 17:21
 * @description:
 **/
const val NOW_PLAYING_CHANNEL_ID = "cn.chitanda.music.media.NOW_PLAYING"
const val NOW_PLAYING_NOTIFICATION_ID = 0x666

class MediaNotificationManager(
    context: Context,
    sessionToken: MediaSessionCompat.Token,
    notificationListener: PlayerNotificationManager.NotificationListener
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val playerNotificationManager: PlayerNotificationManager
    private val imageLoader = context.imageLoader
    private val context = WeakReference(context)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(context)
        }
        val mediaController = MediaControllerCompat(context, sessionToken)
        playerNotificationManager = PlayerNotificationManager.Builder(
            context,
            NOW_PLAYING_NOTIFICATION_ID,
            NOW_PLAYING_CHANNEL_ID
        ).apply {
            setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            setNotificationListener(notificationListener)
            setChannelNameResourceId(R.string.notification_channel)
            setChannelDescriptionResourceId(R.string.notification_channel_description)
        }.build()
        playerNotificationManager.setMediaSessionToken(sessionToken)
        playerNotificationManager.setSmallIcon(R.drawable.ic_notification)
        playerNotificationManager.setUseRewindAction(false)
        playerNotificationManager.setUseFastForwardAction(false)
    }

    private fun createNotificationChannel(
        context: Context,
    ) {
        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannelCompat.Builder(
            NOW_PLAYING_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_LOW
        ).setName(context.getString(R.string.notification_channel))
            .setDescription(context.getString(R.string.notification_channel_description))
            .build()
        notificationManager.createNotificationChannel(channel)
    }

    