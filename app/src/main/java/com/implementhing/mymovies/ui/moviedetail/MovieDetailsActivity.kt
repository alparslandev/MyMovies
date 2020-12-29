package com.implementhing.mymovies.ui.moviedetail

import android.os.Bundle
import coil.load
import com.implementhing.mymovies.R
import com.implementhing.mymovies.databinding.ActivityMovieDetailsBinding
import com.implementhing.mymovies.ui.main.MovieUIModel
import com.implementhing.presentation.BaseActivity
import com.implementhing.presentation.BindingSupport
import com.implementhing.presentation.ViewModelSupport
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding>(),
    ViewModelSupport,
    BindingSupport,
    MovieDetailsViewModel.Presenter{

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    @Inject
    lateinit var viewModel: MovieDetailsViewModel

    override val layoutResId: Int
        get() = R.layout.activity_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.presenter = this

        (intent?.extras?.get(EXTRA_MOVIE) as? MovieUIModel)?.let {
            viewModel.title.set(it.title)
            viewModel.vote.value = it.vote
            viewModel.fetchMovieDetails(it.id)
            binding.ivPoster.load(it.imagePath)
        }
    }
    override fun presentGenres(genres: List<String>) {
        binding.rvGenres.adapter = GenreAdapter(genres)
    }

    override fun back() = onBackPressed()
}