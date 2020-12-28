package com.implementhing.mymovies.ui.main

import android.os.Bundle
import com.implementhing.data.models.Movie
import com.implementhing.data.models.base.MovieBaseResponse
import com.implementhing.mymovies.R
import com.implementhing.mymovies.databinding.ActivityMainBinding
import com.implementhing.presentation.BaseActivity
import com.implementhing.presentation.BindingSupport
import com.implementhing.presentation.ViewModelSupport
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    ViewModelSupport,
    BindingSupport,
    MainViewModel.Presenter {

    override val layoutResId: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.searchMovies("Godfather")
    }

    override fun navigateToDetailsPage() {
        TODO("Not yet implemented")
    }

    override fun presentList(results: List<MovieBaseResponse<Movie>>) {
        TODO("Not yet implemented")
    }
}

