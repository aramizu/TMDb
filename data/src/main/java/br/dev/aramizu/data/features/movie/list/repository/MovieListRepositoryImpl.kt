package br.dev.aramizu.data.features.movie.list.repository

import br.dev.aramizu.data.features.movie.list.datasource.remote.MovieListRemoteDataSource
import br.dev.aramizu.data.features.movie.list.models.MoviesResponse
import io.reactivex.rxjava3.core.Single

internal class MovieListRepositoryImpl(
    private val remoteDataSource: MovieListRemoteDataSource
): MovieListRepository {

    override fun getNowPlaying(): Single<MoviesResponse> = remoteDataSource.getNowPlaying()
}