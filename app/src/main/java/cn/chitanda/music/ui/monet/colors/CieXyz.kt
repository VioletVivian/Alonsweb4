package cn.chitanda.music.ui.monet.colors

data class CieXyz(
    val x: Double,
    val y: Double,
    val z: Double,
) : Color {
    override fun toLinearSrgb(): LinearSrgb {
        retu