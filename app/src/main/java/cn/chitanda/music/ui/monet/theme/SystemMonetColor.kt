package cn.chitanda.music.ui.monet.theme

import android.content.Context
import androidx.annotation.RequiresApi
import cn.chitanda.music.ui.monet.colors.Srgb

@RequiresApi(31)
class SystemMonetColor(private val context: Context) : MonetColor() {
    override val accent1 = getSwatch(ACCENT1_RES)
    override val accent2 = getSwatch(ACC