package cn.chitanda.music.http.bean


import androidx.compose.ui.graphics.Color
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeBanner(
    @Json(name = "banners")
    val banners: List<Banner>? = listOf(),
) {
    @JsonClass(generateAdapter = true)
    data class Banner(
        @Json(name = "adDispatchJson")
        val adDispatchJson: Any? = Any(),
        @Json(name = "adLocation")
        val adLocation: Any? = Any(),
        @Json(name = "adSource")
        val adSource: Any? = Any(),
        @Json(name = "adid")
        val adid: Any? = Any(),
        @Json(name = "adurlV2")
        val adurlV2: Any? = Any(),
        @Json(name = "alg")
        val alg: Any? = Any(),
        @Json(name = "bannerId")
        val bannerId: String? = "",
        @Json(name = "dynamicVideoData")
        val dynamicVideoData: Any? = Any(),
        @Json(name = "encodeId")
        val encodeId: String? = "",
        @Json(name = "event")
        val event: Any? = Any(),
        @Json(name = "exclusive")
        val exclusive: Boolean? = false,
        @Json(name = "extMonitor")
        val extMonitor: Any? = Any(),
        @Json(name = "extMonitorInfo")
        val extMonitorInfo: Any? = Any(),
        @Json(name = "monitorBlackList")
        val monitorBlackList: Any? = Any(),
        @Json(name = "monitorClick")
        val monitorClick: Any? = Any(),
        @Json(name = "monitorClickList")
        val monitorClickList: List<Any?>? = listOf(),
        @Json(name = "monitorImpress")
        val monitorImpress: Any? = Any(),
        @Json(name = "monitorImpressList")
        val monitorImpressList: List<Any?>? = listOf(),
        @Json(name = "monitorType")
        val monitorType: Any? = Any(),
        @Json(name = "pic")
        val pic: String? = "",
        @Json(name = "pid")
        val pid: Any? = Any(),
        @Json(name = "program")
        val program: Any? = Any(),
        @Json(name = "requestId")
        val requestId: String? = "",
        @Json(name = "scm")
        val scm: String? = "",
        @Json(name = "showAdTag")
        val showAdTag: Boolean? = false,
        @Json(name = "showContext")
        val showContext: Any? = Any(),
        @Json(name = "song")
        val song: Song? = Song(),
        @Json(name = "targetId")
        val targetId: String ,
        @Json(name = "targetType")
        val targetType: Int? = 0,
        @Json(name = "titleColor")
        val titleColor: TagColor = TagColor.Red,
        @Json(name = "typeTitle")
        val typeTitle: String? = "",
        @Json(name = "url")
        val url: Any? = Any(),
        @Json(name = "video")
        val video: Any? = Any()
    ) {
        sealed class TagColor(val color: Color) {
            object Red : TagC