package br.dev.aramizu.tmdb.modules

import br.dev.aramizu.tmdb.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {

    viewModel {
        HomeViewModel(
            getMovieListUseCase = get()
        )
    }
}