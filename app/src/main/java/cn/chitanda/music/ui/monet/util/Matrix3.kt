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
        n7, n8, n9,
    ))

    operator fun times(vec: Vector3) = values.let { m ->
        val (r1, r2, r3) = vec

        Vector3(
            m[0]*r1 + m[1]*r2 + m[2]*r3,
            m[3]*r1 + m[4]*r2 + m[5]*r3,
            m[6]*r1 + m[7]*r2 + m[8]*r3,
        )
    }

