package br.dev.aramizu.tmdb

import android.app.Application
import br.dev.aramizu.data.features.movie.modules.movieListRepositoryModules
import br.dev.aramizu.domain.features.movie.modules.movieListUseCaseModules
import br.dev.aramizu.networking.networkingModule
import br.dev.aramizu.tmdb.modules.homeModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                networkingModule,
                homeModules,
                movieListUseCaseModules,
                movieListRepositoryModules
            )
        }
    }
}