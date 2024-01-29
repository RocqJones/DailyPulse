package com.rocqjones.dailypulse

import android.content.res.Resources
import android.os.Build
import kotlin.math.round

class AndroidSystemInfo : Platform {
    override val osName: String
        get() = "Android"
    override val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    override val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    override val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()
}

actual fun logSystemInfo(): Platform = AndroidSystemInfo()