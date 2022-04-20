package com.superfilmes.core.interactors

import com.superfilmes.core.data.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() = movieRepository.getTop250Movies()
}