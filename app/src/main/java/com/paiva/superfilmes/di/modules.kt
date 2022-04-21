package com.paiva.superfilmes.di

import com.paiva.superfilmes.presentation.topmovies.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
  viewModel { HomeViewModel(get(), get()) }
}

