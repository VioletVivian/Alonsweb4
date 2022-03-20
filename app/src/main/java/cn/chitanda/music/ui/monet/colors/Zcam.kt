package cn.chitanda.music.ui.monet.colors

import cn.chitanda.music.ui.monet.util.square
import cn.chitanda.music.ui.monet.util.toDegrees
import cn.chitanda.music.ui.monet.util.toRadians
import kotlin.math.*

// Math code looks better with underscores, and we want to match the paper
@Suppress("LocalVariableName", "PrivatePropertyName", "PropertyName")
data class Zcam(
    // 1D
    val brightness: Double = Double.NaN,
    val lightness: Double = Double.NaN,
    val colorfulness: Double = Double.NaN,
    val chroma: Double = Double.NaN,
    val hueAngle: Double,
    /* hue composition is not implemented */

    // 2D
    val saturation: Double = Double.NaN,
    val vividness: Double = Double.NaN,
    val blackness: Double = Double.NaN,
    val whiteness: Double = Double.NaN,

    val viewingConditions: ViewingConditions,
) : Color {
    override fun toLinearSrgb() = LinearSrgb(Double.NaN, Double.NaN, Double.NaN)

    // Aliases to match the paper
    val Qz: Double get() = brightness
    val Jz: Double get() = lightness
    val Mz: Double get() = colorfulness
    val Cz: Double get() = chroma
    val hz: Double get() = hueAngle
    val Sz: Double get() = saturation
    val Vz: Double get() = vividness
    val Kz: Double get() = blackness
    val Wz: Double get() = whiteness

    fun toCieXyz(
        luminanceSource: LuminanceSource,
        chromaSource: ChromaSource,
    ): CieXyz {
        val cond = viewingConditions
        val Iz_w = cond.Iz_w
        val Qz_w = cond.Qz_w

        /* Step 1 */
        // Achromatic response
        val Iz = when (luminanceSource) {
            LuminanceSource.BRIGHTNESS -> Qz / cond.Iz_coeff
            LuminanceSource.LIGHTNESS -> (Jz * Qz_w) / (cond.Iz_coeff * 100.0)
        }.po