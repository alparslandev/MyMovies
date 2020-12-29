package com.implementhing.data

import com.implementhing.data.models.Movie
import com.implementhing.data.models.MovieDetail
import com.implementhing.data.models.base.BaseListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/movie")
    fun getSearchMoviesByQuery(
        @Query("query") query: String,
        @Query("year") year: Int = 0,
        @Query("include_adult") includeAdult: Boolean = true
    ): Flow<BaseListResponse<Movie>>

    @GET("movie/{id}")
    fun getMovieDetailByMovieId(
        @Path("id") id: Int
    ): Flow<MovieDetail>
}