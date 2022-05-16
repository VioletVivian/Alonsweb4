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
        it.key to Srgb(context.g