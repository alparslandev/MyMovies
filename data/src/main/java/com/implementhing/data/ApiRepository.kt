package com.implementhing.data

import com.implementhing.data.models.Movie
import com.implementhing.data.models.MovieDetail
import com.implementhing.data.models.base.BaseListResponse
import com.implementhing.data.models.base.MovieBaseResponse
import com.implementhing.data.models.request.MovieDetailRequestModel
import com.implementhing.data.models.request.SearchMovieRequestModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getSearchMoviesByQuery(
        request: SearchMovieRequestModel
    ): Flow<BaseListResponse<MovieBaseResponse<Movie>>>

    fun getMovieDetailByMovieId(request: MovieDetailRequestModel): Flow<MovieBaseResponse<MovieDetail>>
}