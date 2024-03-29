package com.implementhing.data

import com.implementhing.data.models.Movie
import com.implementhing.data.models.MovieDetail
import com.implementhing.data.models.base.BaseListResponse
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
    ): Flow<BaseListResponse<Movie>> {
        return apiService.getSearchMoviesByQuery(
            request.query,
            request.page,
            request.year,
            request.includeAdult
        )
    }

    override fun getMovieDetailByMovieId(request: MovieDetailRequestModel): Flow<MovieDetail> {
        return apiService.getMovieDetailByMovieId(request.id)
    }
}