package com.superfilmes.core.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.reflect.TypeToken
import com.superfilmes.core.BaseUnitTest
import com.superfilmes.core.data.MovieDataSource
import com.superfilmes.core.data.MovieRepository
import com.superfilmes.core.domain.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HeroesRepositoryTest: BaseUnitTest() {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    private var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroesRepository: MovieRepository

    private lateinit var heroesDataSource: MovieDataSource


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        heroesDataSource = spyk()
        heroesRepository = spyk(MovieRepository(heroesDataSource))
    }


    @Test
    fun `getHeroes - should return list with three heroes`() = runBlockingTest {
        coEvery { heroesDataSource.getTop250Movies(any()) } returns getMockJson(
            HEROES_JSON, HEROES_TYPE
        )

        assertNotNull(heroesRepository.getTop250Movies())
        assertEquals(6, heroesRepository.getTop250Movies().items.count())

    }

    companion object {
        private const val HEROES_JSON = "movie_response.json"
        private val HEROES_TYPE =
            object : TypeToken<Movie>() {}.type
    }
}