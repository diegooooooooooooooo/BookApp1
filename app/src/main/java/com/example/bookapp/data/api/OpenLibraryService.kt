package com.example.bookapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryService {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("fields") fields: String = "key,title,author_name,first_publish_year,cover_i,isbn,publisher",
        @Query("limit") limit: Int = 20
    ): SearchResponse

    companion object {
        const val BASE_URL = "https://openlibrary.org/"
    }
}
