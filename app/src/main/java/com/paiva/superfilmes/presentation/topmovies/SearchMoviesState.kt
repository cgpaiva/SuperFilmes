package com.paiva.superfilmes.presentation.topmovies

import com.superfilmes.core.domain.MovieItem
import com.superfilmes.core.domain.MovieSearch
import com.superfilmes.core.domain.SearchResult

sealed class SearchMoviesState{
    data class Success(val searchResult: List<SearchResult>): SearchMoviesState()
    data class Error(val exception: Exception): SearchMoviesState()
}
