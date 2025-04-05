package com.rocqjones.dailypulse.articles.data

import com.rocqjones.dailypulse.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {

    suspend fun getArticles(): List<ArticlesRawModel> {
        val response : ArticlesResponse = httpClient.get(
            "${Constants.baseUrl}?country=${Constants.country}&category=${Constants.category}&apiKey=${Constants.apiKey}"
        ).body()
        return response.articles
    }
}