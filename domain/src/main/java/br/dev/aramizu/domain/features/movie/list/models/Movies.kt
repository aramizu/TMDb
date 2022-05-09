package br.dev.aramizu.domain.features.movie.list.models

data class Movies(
    val list: List<Movie>,
    val page: Int
)
