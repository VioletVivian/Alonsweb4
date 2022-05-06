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
            200 to 0.8265622041716898,
            300 to 0.7412252673769428,
            400 to 0.653350946076347,
            500 to 0.5624050605208273,
            600 to 0.48193149058901036,
            700 to 0.39417829080418526,
            800 to 0.3091856317280812,
            900 to 0.22212874192541768,
            1000 to 0.0,
        )

        // Accent chroma from Pixel defaults
        // We use the most chromatic color as the reference
        // cn.chitanda.music.A-1 chrom