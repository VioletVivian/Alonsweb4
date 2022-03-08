package cn.chitanda.music.ui.monet.colors

import cn.chitanda.music.ui.monet.util.cube

data class Oklab(
    override val L: Double,
    override val a: Double,
    override val b: Double,
) : Lab {
    override fun toLinearSrgb(): LinearSrgb {
        val l = oklabToL(this)
        val m = oklabToM(this)
        val s = oklabToS(this)

        return LinearSrgb(
            r = +4.0767416621 * l - 3.3077115913 * m + 0.2309699292 * s,
            g = -1.2684380046 * l + 2.6097574011 * m - 0.3413193965 * s,
            b = -0.0041960863 * l - 0.7034186147 * m + 1.7076147010 * s,
        )
    }

    fun toCieXyz(): CieXyz {
        val l = oklabToL(this)
        val m = oklabToM(this)
        val s = oklabToS(this)

        return CieXyz(
            x = +1.2270138511 * l - 0.5577999807 * m + 0.2812561490 * s,
            y = -0.0405801784 * l + 1.1122568696 * m - 0.0716766787 * s,
            z = -0.0763812845 * l - 0.4214819784 * m + 1.5861632204 * s,
        )
    }

    companion object {
        private fun lmsToOklab(l: Double, m: Double, s: Double): Oklab {
            val lp = Math.cbrt(l)
            val mp = Math.cbrt(m)
            val s