package cn.chitanda.music.repository

import cn.chitanda.music.http.DataState
import cn.chitanda.music.http.RequestStatus
import cn.chitanda.music.http.bean.BaseJson
import kotlinx.coroutines.flow.MutableStateFlow

/**
 *@author: Chen
 *@createTime: 2021/8/31 15:05
 *@description:
 **/
open class BaseRemoteRepository {

    protected suspend fun <T : BaseJson<*>> httpRequest(
        stateFlow: MutableStateFlow<RequestStatus<T>>? = null,
 