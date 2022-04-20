package com.paiva.superfilmes.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.reflect.TypeToken
import com.paiva.superfilmes.BaseUnitTest
import com.paiva.superfilmes.presentation.topmovies.TopMoviesState
import com.paiva.superfilmes.presentation.topmovies.TopMoviesViewModel
import com.superfilmes.core.data.MovieDataSource
import com.superfilmes.core.data.MovieRepository
import com.superfilmes.core.domain.Movie
import com.superfilmes.core.interactors.GetMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TopMoviesViewModelTest: BaseUnitTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var topMovieViewModel: TopMoviesViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieDataSource: MovieDataSource



    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        movieDataSource = spyk()
        movieRepository = spyk(MovieRepository(dataSource = movieDataSource))
        getMoviesUseCase = spyk(GetMoviesUseCase(movieRepository = movieRepository))
        topMovieViewModel = spyk(TopMoviesViewModel(getMoviesUseCase))
    }


    @Test
    fun `getHeroes - should return list with three heroes`()  {
        val moviesMock = getMockJson<Movie>(HEROES_JSON, HEROES_TYPE)
        coEvery { getMoviesUseCase.invoke() } returns moviesMock

        topMovieViewModel.getTopMovies()

    }


    companion object {
        private const val HEROES_JSON = "movie_response.json"
        private val HEROES_TYPE =
            object : TypeToken<Movie>() {}.type
    }
}