package com.implementhing.domain

import com.implementhing.data.models.Movie
import com.implementhing.data.models.base.BaseListResponse
import com.implementhing.data.models.base.MovieBaseResponse
import com.implementhing.data.models.request.SearchMovieRequestModel
import com.implementhing.data.ApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ApiRepository) :
    UseCase<SearchMovieRequestModel, BaseListResponse<MovieBaseResponse<Movie>>> {

    override fun invoke(input: SearchMovieRequestModel): Flow<BaseListResponse<MovieBaseResponse<Movie>>> {
        return repository.getSearchMoviesByQuery(input)
    }
}