package com.rocqjones.dailypulse

import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

class IOSSystemInfo: Platform {
    override val osName: String
        get() = UIDevice.currentDevice.systemName
    override val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    override val deviceModel: String
        get() = UIDevice.currentDevice.model
    override val density: Int
        get() = UIScreen.mainScreen.scale.toInt()
}

actual fun logSystemInfo(): Platform = IOSSystemInfo()