package com.rocqjones.dailypulse

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

actual class Platform() {
    private val myTag = "DailyPulse"

    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logSystemInfo() {
        Log.d(myTag, "Android: $osName, $osVersion, $deviceModel, $density")
    }
}