package com.paiva.superfilmes.presentation.topmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superfilmes.core.interactors.GetMoviesUseCase
import kotlinx.coroutines.launch

class TopMoviesViewModel(private val topMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    private val _topMoviesState = MutableLiveData<TopMoviesState>()

    val topMoviesState: LiveData<TopMoviesState>
        get() = _topMoviesState

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
}