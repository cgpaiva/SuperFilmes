package com.paiva.superfilmes.presentation.topmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paiva.superfilmes.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopMoviesActivity : AppCompatActivity() {
    private val viewModel: TopMoviesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.teste()
    }
}