package com.paiva.superfilmes.presentation.topmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.paiva.superfilmes.databinding.TopMoviesActivityBinding
import com.superfilmes.core.domain.MovieItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopMoviesActivity : AppCompatActivity() {
    private val viewModel: TopMoviesViewModel by viewModel()
    private lateinit var binding: TopMoviesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TopMoviesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getTopMovies()

        configObservers()
    }

    private fun configObservers() {
        viewModel.topMoviesState.observe(this, { state ->
            when (state) {
                is TopMoviesState.Success -> {
                    setUpRecyclerView(state.movieList)
                }
                is TopMoviesState.Error -> {
                    setUpError(state.exception.localizedMessage)
                }

            }
        })
    }

    private fun setUpError(localizedMessage: String?) {

    }

    private fun setUpRecyclerView(movieList: List<MovieItem>) {
        binding.recyclerViewTopMovies.apply {
            layoutManager =
                LinearLayoutManager(this@TopMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = TopMoviesAdapter(movieList)
        }
    }
}