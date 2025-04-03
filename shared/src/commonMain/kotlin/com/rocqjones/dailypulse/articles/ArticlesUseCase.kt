package com.rocqjones.dailypulse.articles

//import kotlinx.datetime.Clock
//import kotlinx.datetime.Instant
//import kotlinx.datetime.TimeZone
//import kotlinx.datetime.daysUntil
//import kotlinx.datetime.toLocalDateTime
//import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(private val repository: ArticlesRepository) {

    suspend fun getArticles(forceFetch : Boolean) : List<ArticleModel> {
        val articlesRaw = repository.getArticles(forceFetch = forceFetch)
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

    /*private fun getDaysAgoStr(date: String) : String? {
        return try {
            val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
            val days = today.daysUntil(
                Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
            )

            val result = when {
                abs(days) > 1 -> "${abs(days)} days ago"
                abs(days) == 1 -> "Yesterday"
                else -> "Today"
            }

            return result
        } catch (e: Exception) {
            print("getSaysAgoStr: ${e.message}")
            null
        }
    }*/
}