package com.implementhing.mymovies.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.implementhing.mymovies.R
import com.implementhing.mymovies.databinding.ActivityMainBinding
import com.implementhing.mymovies.ui.moviedetail.MovieDetailsActivity
import com.implementhing.presentation.BaseActivity
import com.implementhing.presentation.BindingSupport
import com.implementhing.presentation.PaginationListener
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
        binding.rvMovies.addOnScrollListener(object : PaginationListener(binding.rvMovies.layoutManager as GridLayoutManager) {
            override fun loadMoreItems() {
                viewModel.page++
                viewModel.query.value?.let { viewModel.searchMovies(it) }
            }

            override val isLastPage: Boolean
                get() = viewModel.page == viewModel.totalPages
            override val isLoading: Boolean
                get() = movieAdapter.isLoaderVisible
        })
    }

    override fun updateMovies(movies: MutableList<MovieUIModel>) {
        movieAdapter.updateItems(movies)
    }

    override fun addMovies(movies: MutableList<MovieUIModel>) {
        movieAdapter.addItems(movies)
    }

    override fun removeLoading() {
        movieAdapter.removeLoading()
    }

    override fun addLoading() {
        movieAdapter.addLoading()
    }
}

