package com.rocqjones.dailypulse

import kotlinx.coroutines.CoroutineScope

/**
 * 'open' keyword in Kt means that it's not final and other classes are free to extend it & alter behaviour
 */
expect open class BaseViewModel() {

    val scope: CoroutineScope

}