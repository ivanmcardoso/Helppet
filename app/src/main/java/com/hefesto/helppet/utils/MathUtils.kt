package com.hefesto.helppet.utils

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object MathUtils {

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }


fun distance(
    lat_a: Double,
    lng_a: Double,
    lat_b: Double,
    lng_b: Double
): Double {
    val pk = (180f / Math.PI).toFloat()
    val a1 = lat_a / pk
    val a2 = lng_a / pk
    val b1 = lat_b / pk
    val b2 = lng_b / pk
    val t1 =
        cos(a1) * cos(a2) * cos(
            b1
        ) * cos(b2)
    val t2 =
        cos(a1) * sin(a2) * cos(
            b1
        ) * sin(b2)
    val t3 = sin(a1) * sin(b1)
    val tt = acos(t1 + t2 + t3)
    return 6366000 * tt
}
}