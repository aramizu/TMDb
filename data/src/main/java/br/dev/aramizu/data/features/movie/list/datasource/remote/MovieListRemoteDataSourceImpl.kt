package br.dev.aramizu.data.features.movie.list.datasource.remote

import br.dev.aramizu.data.features.movie.list.datasource.remote.api.MovieListService
import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import io.reactivex.rxjava3.core.Single

internal class MovieListRemoteDataSourceImpl(
    private val service: MovieListService
) : MovieListRemoteDataSource {

    override fun getMovieList(trend: String): Single<MoviesResponse> = service.getMovieList(trend)
}