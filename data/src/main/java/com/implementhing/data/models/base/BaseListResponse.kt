package com.implementhing.data.models.base

import com.google.gson.annotations.SerializedName

open class BaseListResponse<TData> (
    @SerializedName("results")
    val results: List<TData>?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)