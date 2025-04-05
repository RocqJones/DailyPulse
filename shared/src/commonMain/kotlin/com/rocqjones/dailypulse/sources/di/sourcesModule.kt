package com.rocqjones.dailypulse.sources.di

import com.rocqjones.dailypulse.sources.application.SourcesUseCase
import com.rocqjones.dailypulse.sources.data.SourcesDataSource
import com.rocqjones.dailypulse.sources.data.SourcesRepository
import com.rocqjones.dailypulse.sources.data.SourcesService
import com.rocqjones.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}