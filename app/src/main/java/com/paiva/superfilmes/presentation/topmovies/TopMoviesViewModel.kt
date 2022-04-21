package com.paiva.superfilmes.presentation.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superfilmes.core.interactors.GetMoviesUseCase
import com.superfilmes.core.interactors.SearchMovieUseCase
import kotlinx.coroutines.launch

class TopMoviesViewModel(
    private val topMoviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _topMoviesState = MutableLiveData<TopMoviesState>()

    val topMoviesState: LiveData<TopMoviesState>
        get() = _topMoviesState

    private val _searchMovieState = MutableLiveData<SearchMoviesState>()

    val searchMovieState: LiveData<SearchMoviesState>
        get() = _searchMovieState

    fun getTopMovies() {
        viewModelScope.launch {
            try {
                var topMoviesList = topMoviesUseCase.invoke()
                _topMoviesState.postValue(TopMoviesState.Success(topMoviesList.items))
            } catch (exception: Exception) {
                _topMoviesState.postValue(TopMoviesState.Error(exception))
            }
        }
    }

    fun searchMovie(name: String) {
        viewModelScope.launch {
            try {
                var foundMovies = searchMoviesUseCase.invoke(name)
                _searchMovieState.postValue(SearchMoviesState.Success(foundMovies.results))
            } catch (exception: Exception) {
                _searchMovieState.postValue(SearchMoviesState.Error(exception))
            }
        }

    }
}