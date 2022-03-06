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

        return Linea