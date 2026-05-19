package com.example.bookapp.data.api

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val start: Int,
    @SerializedName("num_found") val numFound: Int,
    val docs: List<WorkDocument>
)

data class WorkDocument(
    val key: String,
    val title: String,
    @SerializedName("author_name") val authorNames: List<String>? = null,
    @SerializedName("first_publish_year") val firstPublishYear: Int? = null,
    @SerializedName("cover_i") val coverId: Int? = null,
    val isbn: List<String>? = null,
    val publisher: List<String>? = null
)
