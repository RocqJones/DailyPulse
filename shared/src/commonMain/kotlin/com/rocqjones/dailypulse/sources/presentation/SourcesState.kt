package com.rocqjones.dailypulse.sources.presentation

import com.rocqjones.dailypulse.sources.data.Source

data class SourcesState (
    val sources: List<Source>,
    val loading: Boolean = false,
    val error: String? = null
)