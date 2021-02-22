package cn.chitanda.music.http

import cn.chitanda.music.http.bean.BaseJson
import com.squareup.moshi.JsonClass

/**
 *@author: Chen
 *@createTime: 2021/8/31 13:45
 *@description:
 **/
@JsonClass(generateAdapter = true)
data class RequestStatus<T : BaseJson<*>>(
    var code: Int = 0,
    var error: Throwable? = null,
    var msg: String? = null,
    var status: DataState = DataState.STATE_CREATE,
    var json: T? = null,
) {
    val data get() = json?.data
}

enum class DataState {
    STATE_CREATE,//创建
    STATE_LOADING,//加载中
    STATE_SUCCESS,//