package com.implementhing.mymovies.ui.main

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.implementhing.data.models.request.SearchMovieRequestModel
import com.implementhing.domain.SearchUseCase
import com.implementhing.presentation.BaseViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : BaseViewModel() {

    var query = MutableLiveData<String>()
    var presenter: Presenter? = null

    private var timer = Timer()
    private val DELAY: Long = 500


    fun searchMovies(editable: Editable) {
        if (editable.length > 2) {
            timer.cancel()
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    searchMovies(editable.toString())
                }
            }, DELAY)
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchUseCase.invoke(SearchMovieRequestModel(query)).collect {
                val movieUIModels = mutableListOf<MovieUIModel>()
                it.results?.forEach { movie ->
                    movieUIModels.add(MovieUIModel(movie.id!!, "${movie.voteAverage}",
                        movie.title, movie.fullImagePath))
                }
                presenter?.presentList(movieUIModels)

            }
        }
    }

    interface Presenter {
        fun presentList(movies: MutableList<MovieUIModel>)
    }
}