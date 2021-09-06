package br.dev.aramizu.domain.features.movie.list

import br.dev.aramizu.domain.features.movie.list.models.Movies
import io.reactivex.rxjava3.core.Single

interface GetTrendingMovieListUseCase {
    operator fun invoke(trendClassification: String): Single<Movies>
}