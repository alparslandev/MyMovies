package com.implementhing.mymovies.ui.main

import androidx.lifecycle.viewModelScope
import com.implementhing.data.models.Movie
import com.implementhing.data.models.base.MovieBaseResponse
import com.implementhing.data.models.request.SearchMovieRequestModel
import com.implementhing.domain.SearchUseCase
import com.implementhing.presentation.BaseViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : BaseViewModel() {

    var presenter: Presenter? = null

    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchUseCase
                .invoke(SearchMovieRequestModel(query))
                .collect {
                    it.results?.let { movies ->
                        presenter?.presentList(movies)
                    }
                }
        }
    }

    interface Presenter {
        fun navigateToDetailsPage()
        fun presentList(results: List<MovieBaseResponse<Movie>>)
    }
}