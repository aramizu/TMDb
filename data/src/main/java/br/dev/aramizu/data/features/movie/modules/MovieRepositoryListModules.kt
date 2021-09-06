package br.dev.aramizu.data.features.movie.modules

import br.dev.aramizu.data.features.movie.list.datasource.remote.MovieListRemoteDataSource
import br.dev.aramizu.data.features.movie.list.datasource.remote.MovieListRemoteDataSourceImpl
import br.dev.aramizu.data.features.movie.list.datasource.remote.api.MovieListService
import br.dev.aramizu.data.features.movie.list.repository.MovieListRepository
import br.dev.aramizu.data.features.movie.list.repository.MovieListRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val movieListRepositoryModules = module {

    single {
        get<Retrofit>().create(MovieListService::class.java)
    }

    factory<MovieListRemoteDataSource> {
        MovieListRemoteDataSourceImpl(
            service = get()
        )
    }

    factory<MovieListRepository> {
        MovieListRepositoryImpl(
            remoteDataSource = get()
        )
    }
}