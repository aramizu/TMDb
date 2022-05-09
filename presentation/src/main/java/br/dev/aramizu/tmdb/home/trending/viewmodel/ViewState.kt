package br.dev.aramizu.tmdb.home.trending.viewmodel

import br.dev.aramizu.domain.features.movie.list.enums.TrendType
import br.dev.aramizu.domain.features.movie.list.models.Movies

sealed class ViewState() {
    data class ShowMovies(val content: Movies): ViewState()
    data class ShowTrendTypes(val trendList: List<TrendType>): ViewState()
}
