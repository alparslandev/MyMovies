package com.implementhing.domain

import com.implementhing.data.ApiRepository
import com.implementhing.data.models.Movie
import com.implementhing.data.models.base.BaseListResponse
import com.implementhing.data.models.request.SearchMovieRequestModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ApiRepository) :
    UseCase<SearchMovieRequestModel, BaseListResponse<Movie>> {

    override fun invoke(input: SearchMovieRequestModel): Flow<BaseListResponse<Movie>> {
        return repository.getSearchMoviesByQuery(input)
    }
}