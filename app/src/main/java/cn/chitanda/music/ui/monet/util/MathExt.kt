// These simple math functions should always be inlined for performance
@file:Suppress("NOTHING_TO_INLINE")

package cn.chitanda.music.ui.monet.util

import kotlin.math.PI

internal inline fun cube(x: Double) = x * x * x
inter