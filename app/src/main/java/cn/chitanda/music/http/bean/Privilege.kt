
package cn.chitanda.music.http.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Privilege(
    @Json(name = "chargeInfoList")
    val chargeInfoList: List<ChargeInfo>? = null,
    @Json(name = "cp")
    val cp: Int? = null,
    @Json(name = "cs")
    val cs: Boolean? = null,
    @Json(name = "dl")
    val dl: Int? = null,
    @Json(name = "downloadMaxbr")
    val downloadMaxbr: Int? = null,