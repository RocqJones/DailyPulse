package com.rocqjones.dailypulse.articles.data

import com.rocqjones.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticlesRawModel> =
        database.dailyPulseDatabaseQueries.selectArticles(
            mapper = ::mapToArticlesRaw
        ).executeAsList()

    fun insertArticles(article: List<ArticlesRawModel>) {
        try {
            database.dailyPulseDatabaseQueries.transaction {
                article.forEach {
                    insertSingleArticle(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun clearArticles() {
        try {
            database.dailyPulseDatabaseQueries.removeAllArticles()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun insertSingleArticle(it: ArticlesRawModel) {
        try {
            database.dailyPulseDatabaseQueries.insertArticle(
                title = it.title ?: "Title not found",
                desc = it.description,
                date = it.publishedAt ?: "DD/MM/YYYY",
                imageUrl = it.urlToImage
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun mapToArticlesRaw(
        title: String, desc: String?, date: String, url: String?
    ) : ArticlesRawModel = ArticlesRawModel(title, desc, date, url)
}