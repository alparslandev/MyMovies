package com.implementhing.presentation

import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@BindingAdapter("bind:navigationClickListener")
fun bindSetNavigationOnClickListener(view: MaterialToolbar, listener: View.OnClickListener) {
    view.setNavigationOnClickListener(listener)
}

@BindingAdapter("bind:imageUrl")
fun loadImage(layout: ViewGroup, url: String?) {
    url?.let {
        val loader = ImageLoader(layout.context)
        val request = ImageRequest.Builder(layout.context)
            .data(url)
            .allowHardware(false)
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                val result = (loader.execute(request) as SuccessResult).drawable
                val bitmapDrawable = (result as BitmapDrawable).bitmap
                layout.background = bitmapDrawable.toDrawable(layout.resources)
            }
        }
    }
}