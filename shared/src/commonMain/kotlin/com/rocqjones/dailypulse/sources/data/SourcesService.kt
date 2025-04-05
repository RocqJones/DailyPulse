package com.rocqjones.dailypulse.sources.data

import com.rocqjones.dailypulse.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val httpClient: HttpClient) {

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourcesResponse =
            httpClient.get("${Constants.baseUrl}/sources?apiKey=${Constants.apiKey}").body()
        return response.sources
    }
}