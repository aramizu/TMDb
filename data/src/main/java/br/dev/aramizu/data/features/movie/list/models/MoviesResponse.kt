package br.dev.aramizu.data.features.movie.list.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") val page: Int? = null
)
