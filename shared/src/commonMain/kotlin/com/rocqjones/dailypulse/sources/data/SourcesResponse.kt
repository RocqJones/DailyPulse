package com.rocqjones.dailypulse.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status") val status: String,
    @SerialName("sources") val sources: List<SourceRaw>,
)

@Serializable
data class SourceRaw(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val desc: String,
    @SerialName("language")
    val language: String,
    @SerialName("country")
    val country: String,
)

data class Source(
    val id: String,
    val name: String,
    val desc: String,
    val origin: String
)