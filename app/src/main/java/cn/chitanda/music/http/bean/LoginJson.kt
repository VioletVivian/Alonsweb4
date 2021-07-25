
package cn.chitanda.music.http.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginJson(
    @Json(name = "account")
    val account: Account?,
    @Json(name = "bindings")
    val bindings: List<Binding?>?,
    @Json(name = "code")
    override val code: Int,
    @Json(name = "message")
    override val message: String?,
    @Json(name = "msg")
    override val msg: String?,
    @Json(name = "cookie")
    val cookie: String?,
    @Json(name = "loginType")
    val loginType: Int?,
    @Json(name = "profile")
    override val data: Profile?,
) : BaseJson<LoginJson.Profile?>() {
    @JsonClass(generateAdapter = true)
    data class Account(
        @Json(name = "anonimousUser")
        val anonimousUser: Boolean?,
        @Json(name = "ban")
        val ban: Int?,
        @Json(name = "baoyueVersion")
        val baoyueVersion: Int?,
        @Json(name = "createTime")
        val createTime: Long?,
        @Json(name = "donateVersion")
        val donateVersion: Int?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "salt")
        val salt: String?,
        @Json(name = "status")
        val status: Int?,
        @Json(name = "tokenVersion")
        val tokenVersion: Int?,
        @Json(name = "type")
        val type: Int?,
        @Json(name = "userName")
        val userName: String?,
        @Json(name = "vipType")
        val vipType: Int?,
        @Json(name = "viptypeVersion")
        val viptypeVersion: Long?,
        @Json(name = "whitelistAuthority")
        val whitelistAuthority: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Binding(
        @Json(name = "bindingTime")
        val bindingTime: Long?,
        @Json(name = "expired")
        val expired: Boolean?,
        @Json(name = "expiresIn")
        val expiresIn: Int?,
        @Json(name = "id")
        val id: Long?,
        @Json(name = "refreshTime")
        val refreshTime: Int?,
        @Json(name = "tokenJsonStr")
        val tokenJsonStr: String?,
        @Json(name = "type")
        val type: Int?,
        @Json(name = "url")
        val url: String?,
        @Json(name = "userId")
        val userId: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Profile(
        @Json(name = "accountStatus")
        val accountStatus: Int?,
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
        @Json(name = "backgroundImgId")
        val backgroundImgId: Long?,
        @Json(name = "backgroundImgIdStr")
        val backgroundImgIdStr: String?,
        @Json(name = "backgroundUrl")
        val backgroundUrl: String?,