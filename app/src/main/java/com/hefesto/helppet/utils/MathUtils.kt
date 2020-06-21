package com.hefesto.helppet.utils

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object MathUtils {
    fun distances(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val theta = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + (cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return dist
    }

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
        Math.cos(a1) * Math.cos(a2) * Math.cos(
            b1
        ) * Math.cos(b2)
    val t2 =
        Math.cos(a1) * Math.sin(a2) * Math.cos(
            b1
        ) * Math.sin(b2)
    val t3 = Math.sin(a1) * Math.sin(b1)
    val tt = Math.acos(t1 + t2 + t3)
    return 6366000 * tt
}
}