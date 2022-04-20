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
    seedColor: Color,
    chromaFactor: Double = 1.0,
    private val accurateShades: Boolean = true,
) : MonetColor() {
    private val seedNeutral = seedColor.toLinearSrgb().toOklab().toOklch().let { lch ->
        lch.copy(C = lch.C * chromaFactor)
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
        val seedA3 = seedAccent.copy(h = seedAccent.h + ACCENT3_HUE_SHIFT_DEGREES)
        transformSwatch(targets.accent3, seedA3, targets.accent1)
    }

    // Main background color. Tinted with the seed color.
    override val neutral1 by lazy(mode = LazyThread