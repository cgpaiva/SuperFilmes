package com.paiva.superfilmes.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.reflect.TypeToken
import com.paiva.superfilmes.BaseUnitTest
import com.paiva.superfilmes.presentation.topmovies.SearchMoviesState
import com.paiva.superfilmes.presentation.topmovies.TopMoviesState
import com.paiva.superfilmes.presentation.topmovies.HomeViewModel
import com.superfilmes.core.data.MovieDataSource
import com.superfilmes.core.data.MovieRepository
import com.superfilmes.core.domain.Movie
import com.superfilmes.core.domain.MovieSearch
import com.superfilmes.core.interactors.GetMoviesUseCase
import com.superfilmes.core.interactors.SearchMovieUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest : BaseUnitTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var topMovieViewModel: HomeViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieDataSource: MovieDataSource
    private lateinit var searchUseCase: SearchMovieUseCase


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDataSource = spyk()
        movieRepository = spyk(MovieRepository(dataSource = movieDataSource))
        searchUseCase = spyk(SearchMovieUseCase(movieRepository = movieRepository))
        getMoviesUseCase = spyk(GetMoviesUseCase(movieRepository = movieRepository))
        topMovieViewModel = HomeViewModel(getMoviesUseCase, searchUseCase)

    }


    @Test
    fun `getMovies - should return list with six movies`() = runBlocking {
        val moviesMock = getMockJson<Movie>(MOVIES_JSON, MOVIES_TYPE)
        coEvery { getMoviesUseCase.invoke() } returns moviesMock

        topMovieViewModel.getTopMovies()

        Assert.assertEquals(6, getMoviesUseCase.invoke().items.count())

    }

    @Test(expected = Exception::class)
    fun `getMovies - should return exception`() {
        val exception = Assertions.catchException { topMovieViewModel.getTopMovies() }

        topMovieViewModel.getTopMovies()

        Assert.assertEquals(topMovieViewModel.topMoviesState, TopMoviesState.Error(exception))

    }

    @Test
    fun `searchMovies - should return list with two movies`() = runBlocking {
        val moviesMock = getMockJson<MovieSearch>(SEARCH_JSON, SEARCH_TYPE)
        coEvery { searchUseCase.invoke(any()) } returns moviesMock

        topMovieViewModel.searchMovie("Spider Man")

        Assert.assertEquals(2, searchUseCase.invoke("").results.count())

    }

    @Test(expected = Exception::class)
    fun `searchMovies - should return exception`() {
        val exception = Assertions.catchException { topMovieViewModel.searchMovie("") }

        topMovieViewModel.searchMovie("")

        Assert.assertEquals(topMovieViewModel.searchMovieState, SearchMoviesState.Error(exception))

    }


    companion object {
        private const val SEARCH_JSON = "search_response.json"
        private val SEARCH_TYPE =
            object : TypeToken<MovieSearch>() {}.type

        private const val MOVIES_JSON = "movie_response.json"
        private val MOVIES_TYPE =
            object : TypeToken<Movie>() {}.type
    }
}