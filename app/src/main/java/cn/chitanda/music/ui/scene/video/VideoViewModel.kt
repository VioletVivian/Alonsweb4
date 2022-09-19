package cn.chitanda.music.ui.scene.video

/**
 * @author: Chen
 * @createTime: 2021/11/24 4:09 下午
 * @description:
 **/

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chitanda.music.http.RequestStatus
import cn.chitanda.music.http.bean.VideoList
import cn.chitanda.music.http.bean.VideoType
import cn.chitanda.music.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Hi