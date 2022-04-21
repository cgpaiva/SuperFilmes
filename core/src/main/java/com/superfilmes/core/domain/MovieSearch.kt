package com.superfilmes.core.domain

data class MovieSearch (
    val searchType: String,
    val expression: String,
    val results: List<SearchResult>,
    val errorMessage: String
)

data class SearchResult (
    val id: String,
    val resultType: String,
    val image: String,
    val title: String,
    val description: String
)
