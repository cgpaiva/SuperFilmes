package com.paiva.superfilmes.presentation.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.paiva.superfilmes.R
import com.superfilmes.core.domain.MovieItem

class TopMoviesAdapter(
    private val movieList: List<MovieItem>
    ): RecyclerView.Adapter<TopMoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageViewThumbnail = view.findViewById<ImageView>(R.id.imageViewThumbnailMovie)
        private val textViewMovieName = view.findViewById<TextView>(R.id.textViewMovieName)
        private val rankingRateBar = view.findViewById<RatingBar>(R.id.rankingRatingBar)

        fun bind(movie: MovieItem) {
            configImage(movie)
            textViewMovieName.text = movie.title
            rankingRateBar.rating = movie.imDBRating.toFloat() /2

        }

        private fun configImage(movie: MovieItem) {
            Glide
                .with(itemView.context)
                .load(movie.image)
                .apply(
                    RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                )
                .into(imageViewThumbnail);
        }
    }
}