package cn.chitanda.music.ui.scene

import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chitanda.music.http.RequestStatus
import cn.chitanda.music.http.bean.PlaylistJson
import cn.chitanda.music.http.bean.UserProfile
import cn.chitanda.music.preference.PreferenceManager
import cn.chitanda.music.repository.UserRepository
import dagger.hilt.android.li