package com.superfilmes.core.interactors

import com.superfilmes.core.data.MovieRepository


class SearchMovieUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(name: String) = movieRepository.searchMovieWithName(name)
}