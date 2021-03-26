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
        val monitorTyp