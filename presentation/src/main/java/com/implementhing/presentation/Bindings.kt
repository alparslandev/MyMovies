package com.implementhing.presentation

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.MaterialToolbar

@BindingAdapter("bind:navigationClickListener")
fun bindSetNavigationOnClickListener(view: MaterialToolbar, listener: View.OnClickListener) {
    view.setNavigationOnClickListener(listener)
}