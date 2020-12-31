package com.implementhing.mymovies.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.implementhing.common.inflater
import com.implementhing.mymovies.R
import com.implementhing.mymovies.databinding.ItemLoadingBinding
import com.implementhing.mymovies.databinding.ItemMovieBinding

class MovieAdapter(
    private val items: MutableList<MovieUIModel>,
    private val itemClickListener: (MovieUIModel) -> Unit
) : RecyclerView.Adapter<GenericViewHolder>() {

    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    var isLoaderVisible = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return when(viewType) {
            VIEW_TYPE_NORMAL -> MovieHolder(ItemMovieBinding.inflate(parent.inflater, parent, false))
            else -> LoadingHolder(ItemLoadingBinding.inflate(parent.inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_LOADING) return
        else if (holder is MovieHolder) {
            val item = items[position]

            holder.bind(item)
            holder.binding.root.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        when {
            position == items.size - 1 && isLoaderVisible -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_NORMAL
        }

    fun updateItems(moviesToUpdate: MutableList<MovieUIModel>) {
        removeLoading()
        items.clear()
        items.addAll(moviesToUpdate)
        notifyDataSetChanged()
    }

    fun addItems(moviesToAdd: MutableList<MovieUIModel>) {
        removeLoading()
        items.addAll(moviesToAdd)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        items.add(items[items.size - 1])
        notifyItemInserted(items.size - 1)
    }

    fun removeLoading() {
        if (isLoaderVisible) {
            isLoaderVisible = false
            val position = items.size - 1
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = items.size

    class LoadingHolder(val binding: ItemLoadingBinding) : GenericViewHolder(binding)

    class MovieHolder(val binding: ItemMovieBinding) : GenericViewHolder(binding) {
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

open class GenericViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)