package cn.chitanda.music.ui.monet.util

import kotlin.jvm.JvmInline

@JvmInline
value class Matrix3(
    val values: DoubleArray,
) {
    constructor(
        n1: Double, n2: Double, n3: Double,
        n4: Double, n5: Double, n6: Double,
        n7: Double, n8: Double, n9: Double,
    ) : this(doubleArrayOf(
        n1, n2, n3,
        n4, n5, n6,
       