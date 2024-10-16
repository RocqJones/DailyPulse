package com.rocqjones.dailypulse.di

import com.rocqjones.dailypulse.articles.di.articlesModule

/**
 * This is our shared dependency graph
 */
val sharedKoinModule = listOf(
    articlesModule,
    networkModule
)