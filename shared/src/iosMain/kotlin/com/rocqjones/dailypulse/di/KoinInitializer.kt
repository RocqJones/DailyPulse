package com.rocqjones.dailypulse.di

import com.rocqjones.dailypulse.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    try {
        // iOS does not have any VM specific module like Android
        val module =  sharedKoinModule + databaseModule

        startKoin {
            modules(module)
        }
    } catch (e: Exception) {
        println("initKoin: ${e.message}")
    }
}

/**
 * We have to expose the ViewModel to iOS in a separate class because we don't have koin libraries for iOS
 * This will be consumed in the iOS App
 */
class ArticlesInjector : KoinComponent {
    // Inject ArticlesViewModel
    val articlesViewModel: ArticlesViewModel by inject()
}