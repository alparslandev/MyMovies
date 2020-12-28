package com.implementhing.mymovies.ui.moviedetail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.implementhing.data.models.request.MovieDetailRequestModel
import com.implementhing.domain.MovieDetailByIdUseCase
import com.implementhing.presentation.BaseViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailByIdUseCase: MovieDetailByIdUseCase,
) : BaseViewModel() {

    var presenter: Presenter? = null

    var overview = ObservableField<String>()
    var title = ObservableField<String>()
    var vote = MutableLiveData<String>()

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            movieDetailByIdUseCase.invoke(MovieDetailRequestModel(id)).collect {
                it.overview?.let { overviewString ->
                    overview.set(overviewString)
                }
            }
        }
    }

    fun goBack() {
        presenter?.back()
    }

    interface Presenter {
        fun back()
    }
}