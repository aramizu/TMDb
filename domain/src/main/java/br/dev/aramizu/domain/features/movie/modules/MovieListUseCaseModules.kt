package br.dev.aramizu.domain.features.movie.modules

import br.dev.aramizu.domain.features.movie.list.GetMovieListUseCase
import br.dev.aramizu.domain.features.movie.list.GetMovieListUseCaseImpl
import br.dev.aramizu.domain.features.movie.list.mappers.MovieListMapper
import org.koin.dsl.module

val movieListUseCaseModules = module {
    factory<GetMovieListUseCase> {
        GetMovieListUseCaseImpl(
            repository = get(),
            mapper = MovieListMapper()
        )
    }
}