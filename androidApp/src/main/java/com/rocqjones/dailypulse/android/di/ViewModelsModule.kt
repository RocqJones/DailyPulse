package com.rocqjones.dailypulse.android.di

import com.rocqjones.dailypulse.articles.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Android specif dependency graph
 */
val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
}