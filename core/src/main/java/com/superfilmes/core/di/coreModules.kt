package com.superfilmes.core.di

import com.superfilmes.core.BuildConfig
import com.superfilmes.core.data.MovieDataSource
import com.superfilmes.core.data.MovieRepository
import com.superfilmes.core.interactors.GetMoviesUseCase
import com.superfilmes.core.interactors.SearchMovieUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val usesCase = module {
  single { ( MovieRepository(get())) }
  single { GetMoviesUseCase(get()) }
  single { SearchMovieUseCase(get()) }
}

val apiModule = module {
  fun provideUserApi(retrofit: Retrofit): MovieDataSource {
    return retrofit.create(MovieDataSource::class.java)
  }

  single { provideUserApi(get()) }
}

val connectionModule = module {

  fun provideHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()

    return okHttpClientBuilder.build()
  }

  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.GATEWAY_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  single { provideHttpClient() }
  single { provideRetrofit() }

}
