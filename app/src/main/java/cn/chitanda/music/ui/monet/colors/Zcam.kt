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
        }.pow(cond.Qz_denom / (1.6 * cond.F_s))

        /* Step 2 */
        // Chroma
        val Cz = when (chromaSource) {
            ChromaSource.CHROMA -> Cz
            ChromaSource.COLORFULNESS -> Double.NaN // not used
            ChromaSource.SATURATION -> (Qz * square(Sz)) / (100.0 * Qz_w * cond.Qz_denom)
            ChromaSource.VIVIDNESS -> sqrt((square(Vz) - square(Jz - 58)) / 3.4)
            ChromaSource.BLACKNESS -> sqrt((square((100 - Kz) / 0.8) - square(Jz)) / 8)
            ChromaSource.WHITENESS -> sqrt(square(100.0 - Wz) - square(100.0 - Jz))
        }

        /* Step 3 is missing because hue composition is not supported */

        /* Step 4 */
        // ... and back to colorfulness
        val Mz = when (chromaSource) {
            ChromaSource.COLORFULNESS -> Mz
            else -> (Cz * Qz_w) / 100
        }
        val ez = hpToEz(hz)
        val Cz_p = ((Mz * cond.Mz_denom) /
                // Paper specifies pow(1.3514) but this extra precision is necessary for more accurate inversion
                (100.0 * ez.pow(0.068) * cond.ez_coeff)).pow(1.0 / 0.37 / 2)
        val az = Cz_p * cos(hz.toRadians())
        val bz = Cz_p * sin(hz.toRadians())

        /* Step 5 */
        val I = Iz + EPSILON

        val r = pqInv(I + 0.2772100865*az +  0.1160946323*bz)
        val g = pqInv(I)
        val b = pqInv(I + 0.0425858012*az + -0.7538445799*bz)

        val xp =  1.9242264358*r + -1.0047923126*g +  0.0376514040*b
        val yp =  0.3503167621*r +  0.7264811939*g + -0.0653844229*b
        val z  = -0.0909828110*r + -0.3127282905*g +  1.5227665613*b

        val x = (xp + (B - 1)*z) / B
        val y = (yp + (G - 1)*x) / G

        return CieXyz(x, y, z)
    }

    enum class LuminanceSource {
        BRIGHTNESS,
        LIGHTNESS,
    }

    enum class ChromaSource {
        CHROMA,
        COLORFULNESS,
        SATURATION,
        VIVIDNESS,
        BLACKNESS,
        WHITENESS,
    }

    data class ViewingConditions(
        val F_s: Double,

        val L_a: Double,
        val Y_b: Doub