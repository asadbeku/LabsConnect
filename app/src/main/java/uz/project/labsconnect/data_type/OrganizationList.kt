package com.example.myapplication.types.list

import com.google.gson.annotations.SerializedName

data class OrganizationList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)