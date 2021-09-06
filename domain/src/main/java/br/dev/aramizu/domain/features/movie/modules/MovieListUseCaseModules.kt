package br.dev.aramizu.domain.features.movie.modules

import br.dev.aramizu.domain.features.movie.list.GetTrendClassificationUseCase
import br.dev.aramizu.domain.features.movie.list.GetTrendClassificationUseCaseImpl
import br.dev.aramizu.domain.features.movie.list.GetTrendingMovieListUseCase
import br.dev.aramizu.domain.features.movie.list.GetTrendingMovieListUseCaseImpl
import br.dev.aramizu.domain.features.movie.list.mappers.MovieListMapper
import org.koin.dsl.module

val movieListUseCaseModules = module {
    factory<GetTrendingMovieListUseCase> {
        GetTrendingMovieListUseCaseImpl(
            repository = get(),
            mapper = MovieListMapper()
        )
    }

    factory<GetTrendClassificationUseCase> {
        GetTrendClassificationUseCaseImpl()
    }
}