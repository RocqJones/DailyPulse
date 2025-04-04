package com.rocqjones.dailypulse.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "7255fe965b9f417dbc1ad459cb61c2cf"
    private val baseUrl = "https://newsapi.org/v2/top-headlines"

    suspend fun getArticles(): List<ArticlesRawModel> {
        val response : ArticlesResponse = httpClient.get(
            "${baseUrl}?country=$country&category=$category&apiKey=$apiKey"
        ).body()
        return response.articles
    }
}