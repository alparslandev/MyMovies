package com.implementhing.mymovies.ui.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUIModel(
    var id: Int,
    var vote: String?,
    var title: String?,
    var imagePath: String?
): Parcelable