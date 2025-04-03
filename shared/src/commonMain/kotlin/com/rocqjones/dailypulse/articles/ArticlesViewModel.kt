package com.rocqjones.dailypulse.articles

import com.rocqjones.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {

    private val _articlesState : MutableStateFlow<ArticleStateModel> = MutableStateFlow(
        ArticleStateModel(loading = true)
    )

    val articlesState : StateFlow<ArticleStateModel> get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch : Boolean = false) {
        scope.launch {
            _articlesState.emit(
                ArticleStateModel(
                    loading = true,
                    articles = _articlesState.value.articles
                )
            )

            val fetchedArticles = useCase.getArticles(forceFetch = forceFetch)
            _articlesState.emit(ArticleStateModel(articles = fetchedArticles))
        }
    }
}