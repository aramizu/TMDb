package br.dev.aramizu.domain.features.movie.list

import br.dev.aramizu.domain.features.movie.list.enums.TrendType

internal class GetTrendClassificationUseCaseImpl: GetTrendClassificationUseCase {

    override operator fun invoke(): List<TrendType> = listOf(
        TrendType.NOW_PLAYING,
        TrendType.POPULAR,
        TrendType.TOP_RATED,
        TrendType.UPCOMING
    )
}