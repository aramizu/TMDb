package br.dev.aramizu.data.features.movie.list.datasource.remote

import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import io.reactivex.rxjava3.core.Single

internal interface MovieListRemoteDataSource {
    fun getNowPlaying(): Single<MoviesResponse>
}