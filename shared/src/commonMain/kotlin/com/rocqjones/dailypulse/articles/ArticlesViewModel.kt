package com.rocqjones.dailypulse.articles

import com.rocqjones.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {

    /*
    * We'll use reactive programming and streams (in Kt they're Flows)
    * StateFlow (it's observable) emits ArticleState
    * _articlesState: stream of info between VM and UI.
    * ArticleState: LoadingState, SuccessState, ErrorState, & EmptyState.
    * _articlesState: Should not be exposed to the UI directly and we protect it by showing a
      public immutable articles state (articlesState).
    * */
    private val _articlesState : MutableStateFlow<ArticleStateModel> = MutableStateFlow(
        ArticleStateModel(loading = true)
    )

    val articlesState : StateFlow<ArticleStateModel> get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(3000) // represents network delay

            _articlesState.emit(ArticleStateModel(error = "Something went wrong!"))

            delay(3000)

            val fetchedArticles = fetchArticles()

            _articlesState.emit(ArticleStateModel(articles = fetchedArticles))
        }
    }

    private suspend fun fetchArticles() : List<ArticleModel> = mockArticle

    private val mockArticle = listOf(
        ArticleModel(
            "Stock market today: Live updates - CNBC",
            "Futures were higher in premarket trading as Wall Street tried to regain its footing.",
            "2023-11-09",
            "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        ),
        ArticleModel(
            "Best iPhone Deals (2023): Carrier Deals, Unlocked iPhones",
            "Apple's smartphones rarely go on sale, but if you’re looking to upgrade (or you're gift shopping), here are a few cost-saving options.",
            "2023-11-09",
            "https://media.wired.com/photos/622aa5c8cca6acf55fb70b57/191:100/w_1280,c_limit/iPhone-13-Pro-Colors-SOURCE-Apple-Gear.jpg",
        ),
        ArticleModel(
            "Samsung details ‘Galaxy AI’ and a feature that can translate phone calls in real time",
            "In a new blog post, Samsung previewed what it calls “a new era of Galaxy AI” coming to its smartphones and detailed a feature that will use artificial intelligence to translate phone calls in real time.",
            "2023-11-09",
            "https://cdn.vox-cdn.com/thumbor/Ocz_QcxUdtaexp1pPTMygaqzbR8=/0x0:2000x1333/1200x628/filters:focal(1000x667:1001x668)/cdn.vox-cdn.com/uploads/chorus_asset/file/24396795/DSC04128_processed.jpg",
        ),
    )
}