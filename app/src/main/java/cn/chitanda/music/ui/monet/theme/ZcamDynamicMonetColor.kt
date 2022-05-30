
package cn.chitanda.music.ui.monet.theme

import cn.chitanda.music.ui.monet.colors.CieXyz.Companion.toCieXyz
import cn.chitanda.music.ui.monet.colors.Color
import cn.chitanda.music.ui.monet.colors.LinearSrgb
import cn.chitanda.music.ui.monet.colors.Zcam
import cn.chitanda.music.ui.monet.colors.Zcam.Companion.toAbs
import cn.chitanda.music.ui.monet.colors.Zcam.Companion.toRel
import cn.chitanda.music.ui.monet.colors.Zcam.Companion.toZcam
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

class ZcamDynamicMonetColor(
    targets: MonetColor,
    seedColor: Color,
    chromaFactor: Double = 1.0,
    private val cond: Zcam.ViewingConditions,
    private val accurateShades: Boolean = true,
) : MonetColor() {
    private val seedNeutral =
        seedColor.toLinearSrgb().toCieXyz().toAbs(cond).toZcam(cond).let { lch ->
            lch.copy(chroma = lch.chroma * chromaFactor)
        }
    private val seedAccent = seedNeutral

    // Main accent color. Generally, this is close to the seed color.
    override val accent1 by lazy(mode = LazyThreadSafetyMode.NONE) {
        transformSwatch(targets.accent1, seedAccent, targets.accent1)
    }

    // Secondary accent color. Darker shades of accent1.
    override val accent2 by lazy(mode = LazyThreadSafetyMode.NONE) {
        transformSwatch(targets.accent2, seedAccent, targets.accent1)
    }

    // Tertiary accent color. Seed color shifted to the next secondary color via hue offset.
    override val accent3 by lazy(mode = LazyThreadSafetyMode.NONE) {
        val seedA3 = seedAccent.copy(hueAngle = seedAccent.hueAngle + ACCENT3_HUE_SHIFT_DEGREES)
        transformSwatch(targets.accent3, seedA3, targets.accent1)
    }

    // Main background color. Tinted with the seed color.