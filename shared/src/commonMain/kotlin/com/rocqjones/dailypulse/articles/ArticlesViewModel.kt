package com.rocqjones.dailypulse.articles

import com.rocqjones.dailypulse.BaseViewModel
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

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = useCase.getArticles()
            _articlesState.emit(ArticleStateModel(articles = fetchedArticles))
        }
    }
}