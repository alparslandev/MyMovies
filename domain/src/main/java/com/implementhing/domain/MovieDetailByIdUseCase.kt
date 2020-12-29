package com.implementhing.domain

import com.implementhing.data.ApiRepository
import com.implementhing.data.models.MovieDetail
import com.implementhing.data.models.request.MovieDetailRequestModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailByIdUseCase @Inject constructor(private val repository: ApiRepository) :
    UseCase<MovieDetailRequestModel, MovieDetail> {

    override fun invoke(input: MovieDetailRequestModel): Flow<MovieDetail> {
        return repository.getMovieDetailByMovieId(input)
    }
}