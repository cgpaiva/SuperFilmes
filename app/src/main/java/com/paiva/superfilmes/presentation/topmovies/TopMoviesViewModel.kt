package com.paiva.superfilmes.presentation.topmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superfilmes.core.interactors.GetMoviesUseCase
import kotlinx.coroutines.launch

class TopMoviesViewModel(private val topMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    fun teste() {

        viewModelScope.launch {
            try {
                var topMoviesList = topMoviesUseCase.invoke()
                topMoviesList
            } catch (exception: Exception) {
                Log.i("ERRO", "teste: ")
            }

        }
    }
}