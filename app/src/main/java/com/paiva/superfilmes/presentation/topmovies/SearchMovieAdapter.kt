package com.paiva.superfilmes.presentation.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.paiva.superfilmes.R
import com.superfilmes.core.domain.SearchResult

class SearchMovieAdapter(
    private val movieList: List<SearchResult>
) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_movie_item, parent, false)
        return SearchMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class SearchMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageViewThumbnail: ImageView = view.findViewById(R.id.imageViewMovieThumbnail)
        private val textViewMovieName: TextView = view.findViewById(R.id.textViewMovieName)
        private val textViewMovieDescription: TextView = view.findViewById(R.id.textViewMovieDescription)

        fun bind(searchResult: SearchResult) {
            textViewMovieName.text = searchResult.title
            textViewMovieDescription.text = searchResult.description

            configImage(searchResult)

        }

        private fun configImage(searchResult: SearchResult) {
            Glide
                .with(itemView.context)
                .load(searchResult.image)
                .apply(
                    RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                )
                .into(imageViewThumbnail);
        }
    }

}