package br.dev.aramizu.tmdb.home.trending.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.dev.aramizu.domain.features.movie.list.enums.TrendType
import br.dev.aramizu.domain.features.movie.list.enums.TrendType.*
import br.dev.aramizu.domain.features.movie.list.models.Movie
import br.dev.aramizu.tmdb.R

internal class MovieListAdapter(
    val onMovieItemClickListener: (Movie) -> Unit
) : ListAdapter<Movie, MovieListAdapter.ViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                handlerTrendItemClick(currentList[currentPosition])
            }
        }

        private var currentPosition = 0

        private val textViewMovieItem by lazy {
            view.findViewById<TextView>(R.id.textViewMovieItem)
        }

        fun bindView(position: Int) {
            currentPosition = position
            setupMovieItem(currentList[position])
        }

        private fun setupMovieItem(movie: Movie) {
            textViewMovieItem.text = movie.page.toString()
        }

        private fun handlerTrendItemClick(movie: Movie) {
            onMovieItemClickListener.invoke(movie)
        }
    }

    class DiffUtil : ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.page == newItem.page
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }
}
