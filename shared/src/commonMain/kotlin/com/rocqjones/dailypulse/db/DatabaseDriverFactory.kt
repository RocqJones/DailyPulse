package com.rocqjones.dailypulse.db

import app.cash.sqldelight.db.SqlDriver

/*
expect - keyword means Android and iOS must include actual implementation of this class
 */
expect class DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}