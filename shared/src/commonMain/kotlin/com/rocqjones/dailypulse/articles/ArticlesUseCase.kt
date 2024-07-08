package com.rocqjones.dailypulse.articles

class ArticlesUseCase(private val articlesService: ArticlesService) {

    suspend fun getArticles() : List<ArticleModel> {
        val articlesRaw = articlesService.getArticles()
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticlesRawModel>): List<ArticleModel> = articlesRaw.map { raw ->
        ArticleModel(
            title = raw.title ?: "Title not found!",
            desc = raw.description ?: "Click to find out more...",
            date = raw.publishedAt ?: "DD/MM/YYYY",
            imageUrl = raw.urlToImage ?: "https://media.wired.com/photos/622aa5c8cca6acf55fb70b57/191:100/w_1280,c_limit/iPhone-13-Pro-Colors-SOURCE-Apple-Gear.jpg"
        )
    }
}