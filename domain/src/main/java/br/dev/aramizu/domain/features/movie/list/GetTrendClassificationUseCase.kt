package br.dev.aramizu.domain.features.movie.list

import br.dev.aramizu.domain.features.movie.list.enums.TrendType

interface GetTrendClassificationUseCase {
    operator fun invoke(): List<TrendType>
}