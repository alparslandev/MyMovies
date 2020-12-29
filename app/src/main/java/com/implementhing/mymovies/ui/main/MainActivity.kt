package com.implementhing.mymovies.ui.main

import android.content.Intent
import android.os.Bundle
import com.implementhing.mymovies.R
import com.implementhing.mymovies.databinding.ActivityMainBinding
import com.implementhing.mymovies.ui.moviedetail.MovieDetailsActivity
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

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.presenter = this
        movieAdapter = MovieAdapter(mutableListOf()) {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
        binding.rvMovies.adapter = movieAdapter
    }

    override fun presentList(movies: MutableList<MovieUIModel>) {
        movieAdapter.updateItems(movies)
    }
}

