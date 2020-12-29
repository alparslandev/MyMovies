package com.implementhing.mymovies.ui.moviedetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.implementhing.common.inflater
import com.implementhing.mymovies.databinding.ItemGenreBinding

class GenreAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<GenreAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemGenreBinding.inflate(parent.inflater, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.tvGenreTitle.text = items[position]
    }

    override fun getItemCount() = items.size

    class Holder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)
}