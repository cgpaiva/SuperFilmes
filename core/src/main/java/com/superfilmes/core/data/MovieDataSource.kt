package com.superfilmes.core.data

import com.superfilmes.core.domain.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataSource {

    @GET("Top250Movies")
    suspend fun getTop250Movies(
        @Query("apikey") apikey: String,
    ): Movie
}