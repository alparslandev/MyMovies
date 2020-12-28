package com.implementhing.common

import android.view.LayoutInflater
import android.view.ViewGroup

val ViewGroup.inflater: LayoutInflater
    get() {
        return LayoutInflater.from(context)
    }