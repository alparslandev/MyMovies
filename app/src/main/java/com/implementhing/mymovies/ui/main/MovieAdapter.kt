package com.implementhing.mymovies.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.implementhing.common.inflater
import com.implementhing.mymovies.R
import com.implementhing.mymovies.databinding.ItemMovieBinding

class MovieAdapter(
    private val items: MutableList<MovieUIModel>,
    private val itemClickListener: (MovieUIModel) -> Unit
) : RecyclerView.Adapter<MovieAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemMovieBinding.inflate(parent.inflater, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        holder.bind(item)
        holder.binding.root.setOnClickListener {
            itemClickListener.invoke(item)
        }
    }

    fun addItems(moviesToAdd: MutableList<MovieUIModel>) {
        val firstRange = items.size
        items.addAll(moviesToAdd)
        notifyItemRangeInserted(firstRange - 1, moviesToAdd.size)
    }

    override fun getItemCount() = items.size

    class Holder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieUIModel) {
            binding.tvVote.text = item.vote
            binding.tvTitle.text = item.title
            binding.ivPoster.load(item.imagePath) {
                placeholder(R.drawable.placeholder)
                crossfade(true)
            }
        }
    }
}