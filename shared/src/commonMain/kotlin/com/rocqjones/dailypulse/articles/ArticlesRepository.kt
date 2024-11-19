package com.rocqjones.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {

    suspend fun getArticles() : List<ArticlesRawModel> {
        val articlesDb = dataSource.getAllArticles()
        println("articlesDb: ${articlesDb.size}")

        if (articlesDb.isEmpty()) {
            val fetchedArticles = service.getArticles()
            dataSource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return articlesDb
    }
}