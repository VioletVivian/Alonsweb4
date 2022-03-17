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