package com.superfilmes.core.data

import com.superfilmes.core.BuildConfig

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun getTop250Movies() =
        dataSource.getTop250Movies(BuildConfig.PUBLIC_API_KEY)

}