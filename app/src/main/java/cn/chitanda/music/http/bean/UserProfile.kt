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
        @Json(name = "allSubscribedCount")
        val allSubscribedCount: Int?,
        @Json(name = "artistIdentity")
        val artistIdentity: List<Any?>?,
        @Json(name = "authStatus")
        val authStatus: Int?,
        @Json(name = "authority")
        val authority: Int?,
        @Json(name = "avatarDetail")
        val avatarDetail: Any?,
        @Json(name = "avatarImgId")
        val avatarImgId: Long?,
        @Json(name = "avatarImgIdStr")
        val avatarImgIdStr: String?,
        @Json(name = "avatarUrl")
        val avatarUrl: String?,
        @Json(name = "backg