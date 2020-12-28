package com.implementhing.data.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
)