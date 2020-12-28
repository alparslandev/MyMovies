package com.implementhing.data

import com.implementhing.data.models.Movie
import com.implementhing.data.models.MovieDetail
import com.implementhing.data.models.base.BaseListResponse
import com.implementhing.data.models.base.MovieBaseResponse
import com.implementhing.data.models.request.MovieDetailRequestModel
import com.implementhing.data.models.request.SearchMovieRequestModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class ApiRepositoryImpl constructor(
    private val apiService: ApiService
) : ApiRepository {
    override fun getSearchMoviesByQuery(
        request: SearchMovieRequestModel
    ): Flow<BaseListResponse<MovieBaseResponse<Movie>>> {
        return apiService.getSearchMoviesByQuery(request.query, request.year, request.includeAdult)
    }

    override fun getMovieDetailByMovieId(request: MovieDetailRequestModel): Flow<MovieBaseResponse<MovieDetail>> {
        return apiService.getMovieDetailByMovieId(request.id)
    }
}