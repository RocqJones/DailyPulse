package com.rocqjones.dailypulse.articles.presentation

import com.rocqjones.dailypulse.articles.data.ArticleModel

data class ArticleStateModel(
    val articles: List<ArticleModel> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)