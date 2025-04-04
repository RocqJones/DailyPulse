package com.rocqjones.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.rocqjones.dailypulse.db.DailyPulseDatabase
import com.rocqjones.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}