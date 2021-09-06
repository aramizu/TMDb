package br.dev.aramizu.tmdb.home.viewmodel

sealed class ActionState {
    object ShowToast : ActionState()
}