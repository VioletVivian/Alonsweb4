package cn.chitanda.music.http.bean


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class VideoList(
    override val code: Int,
    override val message: String?,
    override val msg: String?, @Json(name = "hasmore")
    val hasMore: Boolean,
    @Json(name = "datas")
    override val data: List<Data>?
) : BaseJson<List<VideoList.Data>?>() {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "alg")
        val alg: String?,
        @Json(name = "data")
        val info: Info?,
        @Json(name = "displayed")
        val displayed: Bo