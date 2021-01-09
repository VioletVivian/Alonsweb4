package cn.chitanda.music

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chitanda.music.media.connect.MusicServiceConnection
import cn.chitanda.music.media.extensions.currentPlayBackPosition
import cn.chitanda.music.media.extensions.duration
import cn.chitanda.music.media.extensions.id
import cn.chitanda.music.media.extensions.isPlayEnabled
import cn.chitanda.music.media.extensions.isPlaying
import cn.chitanda.music.media.extensions.isPrepared
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

/**
 * @author: Chen
 * @createTime: 2022/1/24 17:01
 * @description:
 **/
private const val TAG = "MusicViewModel"
private const val UPDATE_CURRENT_MUSIC_PLAY_POSITION = 100L
@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
) : ViewModel() {
    val nowPlaying: LiveData<MediaMetadataCompat> = musicServiceConnection.nowPlaying
    val playbackState: LiveData<PlaybackStateCompat> = musicServiceConnection.playbackState
    private var _currentPlaylist = ""
    private val _currentPosition = MutableLiveData(0L)
    val currentPosition: LiveData<Long> get() = _currentPosition

    fun play(playlist: String, mediaId: String?, pauseAllowed: Boolean = true) {

        val nowPlaying = nowPlaying.value
        val transportControls = musicServiceConnection.transportControls

        val isPrepared = musicServiceConnection.playbackState.value?.isPrepared ?: false
        when {
            isPrepared && playlist == _currentPlaylist && mediaId == nowPlaying?.id -> {
                musicServiceConnection.playbackState.value?.let { playbackState ->
                    when {
                        playbackState.isPlaying ->
                           