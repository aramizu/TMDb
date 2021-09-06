package br.dev.aramizu.data.features.movie.list.datasource.remote.api

import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

internal interface MovieListService {

    @GET("movie/now_playing")
    fun getNowPlaying() : Single<MoviesResponse>
}