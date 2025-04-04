package com.rocqjones.dailypulse.articles.di

import com.rocqjones.dailypulse.articles.ArticlesDataSource
import com.rocqjones.dailypulse.articles.ArticlesRepository
import com.rocqjones.dailypulse.articles.ArticlesService
import com.rocqjones.dailypulse.articles.ArticlesUseCase
import com.rocqjones.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

/**
 * This will be the injector of all the classes.
 * Will contain instantiation of all the dependencies regarding the articles
 */

val articlesModule = module {
    /*
    Single: only single dependency will be created for each object
     1. Service
     2. User case init
     3. This Vm will only work for iOS because Android uses it's only VM mechanism
     */
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}