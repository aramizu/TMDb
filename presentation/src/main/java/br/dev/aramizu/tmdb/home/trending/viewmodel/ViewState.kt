package br.dev.aramizu.tmdb.home.trending.viewmodel

import br.dev.aramizu.domain.features.movie.list.enums.TrendType

sealed class ViewState() {
    data class ShowContent(val content: String): ViewState()
    data class ShowTrend(val trendList: List<TrendType>): ViewState()
}
