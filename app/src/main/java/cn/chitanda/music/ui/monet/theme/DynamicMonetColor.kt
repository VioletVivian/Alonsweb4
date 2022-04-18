package cn.chitanda.music.ui.monet.theme

import cn.chitanda.music.ui.monet.colors.Color
import cn.chitanda.music.ui.monet.colors.Lch
import cn.chitanda.music.ui.monet.colors.Oklab.Companion.toOklab
import cn.chitanda.music.ui.monet.colors.OklabGamut
import cn.chitanda.music.ui.monet.colors.OklabGamut.clipToLinearSrgb
import cn.chitanda.music.ui.monet.colors.Oklch
import cn.chitanda.music.ui.monet.colors.Oklch.Companion.toOklch

class DynamicMonetColor(
    targets: MonetColor,
    seedColor