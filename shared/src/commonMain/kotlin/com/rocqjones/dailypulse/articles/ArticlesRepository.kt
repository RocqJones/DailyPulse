package com.rocqjones.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {

    suspend fun getArticles(forceFetch : Boolean) : List<ArticlesRawModel> {

        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = dataSource.getAllArticles()
        println("articlesDb: ${articlesDb.size}")

        if (articlesDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesDb
    }

    suspend fun fetchArticles(): List<ArticlesRawModel> {
        val fetchedArticles = service.getArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}