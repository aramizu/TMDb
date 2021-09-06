package br.dev.aramizu.tmdb.home.trending.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.dev.aramizu.domain.features.movie.list.enums.TrendType
import br.dev.aramizu.domain.features.movie.list.enums.TrendType.*
import br.dev.aramizu.tmdb.R

internal class TrendListAdapter(
    val onTrendItemClickListener: (TrendType) -> Unit
) : ListAdapter<TrendType, TrendListAdapter.ViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_trend_item, parent, false)
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

        private val textViewTrendItem by lazy {
            view.findViewById<TextView>(R.id.textViewTrendItem)
        }

        fun bindView(position: Int) {
            currentPosition = position
            setupTrendItem(currentList[position])
        }

        private fun setupTrendItem(trendType: TrendType) {
            textViewTrendItem.text = getTrendDescription(trendType)
        }

        private fun getTrendDescription(trendType: TrendType): String {
            val resource = when (trendType) {
                NOW_PLAYING -> R.string.trend_now_playing
                POPULAR -> R.string.trend_popular
                TOP_RATED -> R.string.trend_top_rated
                UPCOMING -> R.string.trend_upcoming
            }

            return view.context.getString(resource)
        }

        private fun handlerTrendItemClick(
            trendType: TrendType
        ) {
            onTrendItemClickListener.invoke(trendType)
        }
    }

    class DiffUtil : ItemCallback<TrendType>() {
        override fun areItemsTheSame(oldItem: TrendType, newItem: TrendType): Boolean {
            return oldItem.value == newItem.value
        }

        override fun areContentsTheSame(
            oldItem: TrendType,
            newItem: TrendType
        ): Boolean {
            return oldItem == newItem
        }
    }
}
