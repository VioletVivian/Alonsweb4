package cn.chitanda.music.ui.monet.theme

import cn.chitanda.music.ui.monet.colors.Color
import cn.chitanda.music.ui.monet.colors.Oklch

/*
 * Default target colors, conforming to Material You standards.
 *
 * Derived from AOSP and Pixel defaults.
 */
class MaterialYouTargets(
    private val chromaFactor: Double = 1.0,
) : MonetColor() {
    companion object {
        // Lightness from AOSP defaults
        private val LIGHTNESS_MAP = mapOf(
            0 to 1.0,
            10 to 0.9880873963836093,
            50 to 0.9551400440214246,
            100 to 0.9127904082618294,
 