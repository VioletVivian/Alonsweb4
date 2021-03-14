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
  