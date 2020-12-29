package com.implementhing.data.models.request

data class SearchMovieRequestModel(
    val query: String,
    val year: Int = 0,
    val includeAdult: Boolean = false,
    val page: Int = 1
)