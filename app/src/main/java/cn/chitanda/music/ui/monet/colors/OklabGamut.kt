
package cn.chitanda.music.ui.monet.colors

import cn.chitanda.music.ui.monet.colors.Oklab.Companion.toOklab
import cn.chitanda.music.ui.monet.util.cube
import cn.chitanda.music.ui.monet.util.square
import kotlin.math.*

/**
 * Ported from the original C++ implementation:
 *
 * Copyright (c) 2021 Björn Ottosson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR cn.chitanda.music.A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
// Renaming variables hurts the readability of math code
@Suppress("LocalVariableName")
object OklabGamut {
    private const val CLIP_EPSILON = 0.00001

    // Finds the maximum saturation possible for a given hue that fits in sRGB
    // Saturation here is defined as S = C/L
    // a and b must be normalized so a^2 + b^2 == 1
    private fun computeMaxSaturation(a: Double, b: Double): Double {
        // Max saturation will be when one of r, g or b goes below zero.

        // Select different coefficients depending on which component goes below zero first
        val coeff = when {
            -1.88170328 * a - 0.80936493 * b > 1 -> SaturationCoefficients.RED
            1.81444104 * a - 1.19445276 * b > 1 -> SaturationCoefficients.GREEN
            else -> SaturationCoefficients.BLUE
        }

        // Approximate max saturation using a polynomial:
        val S = coeff.k0 + coeff.k1 * a + coeff.k2 * b + coeff.k3 * a * a + coeff.k4 * a * b

        // Do one step Halley's method to get closer
        // this gives an error less than 10e6, except for some blue hues where the dS/dh is close to infinite
        // this should be sufficient for most applications, otherwise do two/three steps

        val k_l = +0.3963377774 * a + 0.2158037573 * b
        val k_m = -0.1055613458 * a - 0.0638541728 * b
        val k_s = -0.0894841775 * a - 1.2914855480 * b

        run {
            val l_ = 1 + S * k_l
            val m_ = 1 + S * k_m
            val s_ = 1 + S * k_s

            val l = cube(l_)
            val m = cube(m_)
            val s = cube(s_)

            val l_dS = 3 * k_l * square(l_)
            val m_dS = 3 * k_m * square(m_)
            val s_dS = 3 * k_s * square(s_)

            val l_dS2 = 6 * square(k_l) * l_
            val m_dS2 = 6 * square(k_m) * m_
            val s_dS2 = 6 * square(k_s) * s_

            val f  = coeff.wl * l     + coeff.wm * m     + coeff.ws * s
            val f1 = coeff.wl * l_dS  + coeff.wm * m_dS  + coeff.ws * s_dS
            val f2 = coeff.wl * l_dS2 + coeff.wm * m_dS2 + coeff.ws * s_dS2

            return S - f * f1 / (f1*f1 - 0.5 * f * f2)