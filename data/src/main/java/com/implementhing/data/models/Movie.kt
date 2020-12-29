package com.implementhing.data.models

import com.google.gson.annotations.SerializedName
import com.implementhing.data.models.base.MovieBaseResponse

data class Movie(
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
): MovieBaseResponse()