package br.dev.aramizu.data.features.movie.list.repository

import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import io.reactivex.rxjava3.core.Single

interface MovieListRepository {
    fun getMovieList(trend: String): Single<MoviesResponse>
}