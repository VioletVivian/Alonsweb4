package cn.chitanda.music.ui.monet.theme

import cn.chitanda.music.ui.monet.colors.Color

typealias ColorSwatch = Map<Int, Color>

abstract class MonetColor {
    abstract val neutral1: ColorSwatch
    abstract val neutral2: ColorSwatch

    abstract val accent1: ColorSwatch
    abstract val accent2: ColorSwatch
    abstract val accent3: ColorSwa