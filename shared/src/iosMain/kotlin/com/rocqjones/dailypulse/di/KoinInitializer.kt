package com.rocqjones.dailypulse.di

import com.rocqjones.dailypulse.articles.data.ArticlesDataSource
import com.rocqjones.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    // iOS does not have any VM specific module like Android
    val module =  sharedKoinModule + databaseModule

    startKoin {
        modules(module)
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

/*
class SourcesInjector : KoinComponent {
    val sourcesViewModel: SourcesViewModel by inject()
}*/
