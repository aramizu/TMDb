package br.dev.aramizu.data.features.movie.list.datasource.remote.api

import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MovieListService {

    @GET("movie/{trend}")
    fun getMovieList(@Path("trend") trend: String) : Single<MoviesResponse>
}