package com.rocqjones.dailypulse.di

import com.rocqjones.dailypulse.articles.di.articlesModule
import com.rocqjones.dailypulse.sources.di.sourcesModule

/**
 * This is our shared dependency graph
 */
val sharedKoinModule = listOf(
    articlesModule,
    networkModule,
    sourcesModule
)