package com.implementhing.data.models.base

import com.google.gson.annotations.SerializedName

open class MovieBaseResponse<T>(
    var data: T,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
) {
    val fullImagePath: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
    val fullBackdropPath: String
        get() = "https://image.tmdb.org/t/p/w500$backdropPath"
}