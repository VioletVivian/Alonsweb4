package cn.chitanda.music.http.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class UserProfile(
    override val code: Int,
    override val message: String?,
    override val msg: String?,
    @Json(name = "profile")
    override val data: Data?,
    @Json(name = "level")
    val level: Int?
) : BaseJson<UserProfile.Data?>() {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "accountStatus")
        val accountStatus: Int?,
        @Json(nam