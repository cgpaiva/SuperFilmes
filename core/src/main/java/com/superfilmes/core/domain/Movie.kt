
package com.superfilmes.core.domain

import com.google.gson.annotations.SerializedName

data class Movie (
    val items: List<MovieItem>,
    val errorMessage: String
)

data class MovieItem (
    val id: String,
    val rank: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,

    @SerializedName("imDbRating")
    val imDBRating: String,

    @SerializedName("imDbRatingCount")
    val imDBRatingCount: String
)
