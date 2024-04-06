package com.rocqjones.dailypulse.articles

data class ArticleStateModel(
    val articles: List<ArticleModel> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)