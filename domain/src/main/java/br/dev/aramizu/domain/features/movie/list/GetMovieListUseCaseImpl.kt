package br.dev.aramizu.domain.features.movie.list

import br.dev.aramizu.data.features.movie.list.repository.MovieListRepository
import br.dev.aramizu.domain.features.movie.list.mappers.MovieListMapper
import br.dev.aramizu.domain.features.movie.list.models.Movies
import io.reactivex.rxjava3.core.Single

internal class GetMovieListUseCaseImpl(
    private val repository: MovieListRepository,
    private val mapper: MovieListMapper
): GetMovieListUseCase {

    override fun getNowPlaying(): Single<Movies> = repository.getNowPlaying()
        .map(mapper::map)
}