package cn.chitanda.music.http.bean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist(
    @Json(name = "albumSize")
    val albumSize: Int? = 0,
    @Json(name = "alias")
    val alias: List<Any?>? = listOf(),
    @Json(name = "briefDesc")
    val briefDesc: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "img1v1Id")