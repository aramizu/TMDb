package br.dev.aramizu.tmdb.home.trending.viewmodel

sealed class ActionState {
    object ShowToast : ActionState()
}