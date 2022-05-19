package cn.chitanda.music.ui.monet.theme

import android.content.Context
import androidx.annotation.RequiresApi
import cn.chitanda.music.ui.monet.colors.Srgb

@RequiresApi(31)
class SystemMonetColor(private val context: Context) : MonetColor() {
    override val accent1 = getSwatch(ACCENT1_RES)
    override val accent2 = getSwatch(ACCENT2_RES)
    override val accent3 = getSwatch(ACCENT3_RES)

    override val neutral1 = getSwatch(NEUTRAL1_RES)
    override val neutral2 = getSwatch(NEUTRAL2_RES)

    private fun getSwatch(ids: Map<Int, Int>) = ids.map {
        it.key to Srgb(context.getColor(it.value))
    }.toMap()

    companion object {
        val ACCENT1_RES = mapOf(
            0 to android.R.color.system_accent1_0,
            10 to android.R.color.system_accent1_10,
            50 to android.R.color.system_accent1_50,
            100 to android.R.color.system_accent1_100,
            200 to android.R.color.system_accent1_200,
            300 to android.R.color.system_accent1_300,
            400 to android.R.color.system_accent1_400,
            500 to android.R.color.system_accent1_500,
            600 to android.R.color.system_accent1_600,
            700 to android.R.color.system_accent1_700,
            800 to android.R.color.system_accent1_800,
            900 to android.R.color.system_accent1_900,
            1000 to android.R.color.system_accent1_1000,
        )

        val ACCENT2_RES = mapOf(
            0 to android.R.color.system_accent2_0,
            10 to android.R.color.system_accent2_10,
            50 to android.R.color.system_accent2_50,
            100 to android.R.color.system_accent2_100,
            200 to android.R.color.system_accent2_200,
            300 to android.R.color.system_accent2_300,
            400 to android.R.color.system_accent2_400,
            500 to android.R.color.syste