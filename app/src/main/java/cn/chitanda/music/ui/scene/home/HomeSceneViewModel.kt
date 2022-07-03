package cn.chitanda.music.ui.scene.home

import androidx.lifecycle.ViewModel
import cn.chitanda.music.extension.launchFlow
import cn.chitanda.music.repository.HomeRepository
import cn.chitanda.music.ui.scene.PageState
import cn.chitanda.music.utils.setStat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 *@author: Chen
 *@createTime: 2021/9/2 13:47
 *@description:
 **/
private const val TAG = "HomeSceneViewModel"

@HiltViewModel
class HomeSceneViewMode