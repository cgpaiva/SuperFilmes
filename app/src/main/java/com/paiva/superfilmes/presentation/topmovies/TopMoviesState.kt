package com.paiva.superfilmes.presentation.topmovies

import com.superfilmes.core.domain.MovieItem

sealed class TopMoviesState {
    data class Success(val movieList: List<MovieItem>): TopMoviesState()
    data class Error(val exception: Exception): TopMoviesState()
}