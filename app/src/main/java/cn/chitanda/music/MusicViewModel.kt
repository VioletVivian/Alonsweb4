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
import javax.inj