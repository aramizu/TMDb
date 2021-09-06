package br.dev.aramizu.tmdb.home.viewmodel

sealed class ViewState() {
    data class ShowContent(val content: String): ViewState()
}
