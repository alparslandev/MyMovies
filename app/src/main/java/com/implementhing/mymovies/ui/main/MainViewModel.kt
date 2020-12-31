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

    private val START_PAGE = 1
    var page: Int = START_PAGE
    var totalPages: Int = -1

    fun searchMovies(editable: Editable) {
        if (editable.length > 2) {
            timer.cancel()
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    page = START_PAGE
                    searchMovies(editable.toString())
                }
            }, DELAY)
        }
    }

    fun searchMovies(query: String) {
        if (query == this.query.value && page == totalPages) return

        viewModelScope.launch {
            searchUseCase.invoke(SearchMovieRequestModel(query = query, page = page)).collect { response ->
                val movieUIModels = mutableListOf<MovieUIModel>()
                page = response.page ?: START_PAGE
                totalPages = response.totalPages ?: -1
                response.results?.forEach { movie ->
                    movieUIModels.add(MovieUIModel(movie.id!!, "${movie.voteAverage}",
                        movie.title, movie.fullImagePath))
                }
                presenter?.presentList(movieUIModels)

                if (page == totalPages) {
                    presenter?.removeLoading()
                } else if (page < totalPages) {
                    presenter?.addLoading()
                }
            }
        }
    }

    interface Presenter {
        fun presentList(movies: MutableList<MovieUIModel>)
        fun removeLoading()
        fun addLoading()
    }
}