package com.paiva.superfilmes.presentation.topmovies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.paiva.superfilmes.databinding.TopMoviesActivityBinding
import com.superfilmes.core.domain.MovieItem
import com.superfilmes.core.domain.SearchResult
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
        configListeners()
    }

    private fun configListeners() {
        binding.cardViewSearchMovie.setOnClickListener {
            if (binding.editTextSearchMovie.text.isNullOrBlank()){
                Toast.makeText(this, "É necessário preencher o campo de busca", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            binding.progressBarSearchMovie.visibility = View.VISIBLE
            binding.cardViewSearchMovie.visibility = View.GONE
            viewModel.searchMovie(binding.editTextSearchMovie.text.toString())
        }
    }

    private fun configObservers() {
        viewModel.topMoviesState.observe(this, { state ->
            when (state) {
                is TopMoviesState.Success -> {
                    binding.progressBarTopMovies.visibility = View.GONE
                    setUpTopMoviesRecyclerView(state.movieList)
                }
                is TopMoviesState.Error -> {
                    setUpError(state.exception.localizedMessage)
                }

            }
        })

        viewModel.searchMovieState.observe(this, { state ->
            when (state) {
                is SearchMoviesState.Success -> {
                    binding.progressBarSearchMovie.visibility = View.GONE
                    binding.cardViewSearchMovie.visibility = View.VISIBLE
                    configRecyclerviewSearchMovie(state.searchResult)
                }
                is SearchMoviesState.Error -> {
                    setUpError(state.exception.localizedMessage)
                }
            }
        })
    }


    private fun setUpError(localizedMessage: String?) {

    }

    private fun setUpTopMoviesRecyclerView(movieList: List<MovieItem>) {
        binding.recyclerViewTopMovies.apply {
            layoutManager =
                LinearLayoutManager(this@TopMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = TopMoviesAdapter(movieList)
        }
    }

    private fun configRecyclerviewSearchMovie(searchResult: List<SearchResult>) {
        binding.recyclerViewSearchMovies.apply {
            layoutManager = LinearLayoutManager(this@TopMoviesActivity)
            adapter = SearchMovieAdapter(searchResult)
        }
    }
}