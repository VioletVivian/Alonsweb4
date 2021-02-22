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
    var 