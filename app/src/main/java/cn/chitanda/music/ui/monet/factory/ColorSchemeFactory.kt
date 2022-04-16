
package cn.chitanda.music.ui.monet.factory

import cn.chitanda.music.ui.monet.colors.CieLab
import cn.chitanda.music.ui.monet.colors.Color
import cn.chitanda.music.ui.monet.colors.Illuminants
import cn.chitanda.music.ui.monet.colors.Zcam
import cn.chitanda.music.ui.monet.theme.MonetColor
import cn.chitanda.music.ui.monet.theme.DynamicMonetColor
import cn.chitanda.music.ui.monet.theme.MaterialYouTargets
import cn.chitanda.music.ui.monet.theme.ZcamDynamicMonetColor
import cn.chitanda.music.ui.monet.theme.ZcamMaterialYouTargets

/**
 * Modified from [ColorSchemeFactory](https://github.com/kdrag0n/android12-extensions/blob/main/app/src/main/java/dev/kdrag0n/android12ext/xposed/hooks/ColorSchemeFactory.kt)
 * in the original Xposed module. Allows extending or advanced usage for creating custom color schemes,
 * or using ZCam
 */

interface ColorSchemeFactory {

    fun getColor(color: Color): MonetColor

    companion object {
        fun getFactory(
            useZcam: Boolean,
            // For all models
            chromaFactor: Double,
            accurateShades: Boolean,
            // ZCAM only
            whiteLuminance: Double,
            useLinearLightness: Boolean,
        ) = if (useZcam) {
            val cond = createZcamViewingConditions(whiteLuminance)

            object : ColorSchemeFactory {
                override fun getColor(color: Color) = ZcamDynamicMonetColor(
                    targets = ZcamMaterialYouTargets(
                        chromaFactor = chromaFactor,
                        useLinearLightness = useLinearLightness,
                        cond = cond,
                    ),