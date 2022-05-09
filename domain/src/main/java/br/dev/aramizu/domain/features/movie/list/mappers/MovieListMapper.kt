package br.dev.aramizu.domain.features.movie.list.mappers

import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import br.dev.aramizu.domain.features.movie.list.models.Movies

internal class MovieListMapper {

    fun map(response: MoviesResponse): Movies {
        return Movies(
            page = response.page ?: 0,
            list = listOf()
        )
    }
}