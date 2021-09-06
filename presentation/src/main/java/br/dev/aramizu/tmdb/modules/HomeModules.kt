package br.dev.aramizu.tmdb.modules

import br.dev.aramizu.tmdb.home.trending.viewmodel.TrendingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {

    viewModel {
        TrendingViewModel(
            getTrendingMovieListUseCase = get(),
            getTrendClassificationUseCase = get()
        )
    }
}