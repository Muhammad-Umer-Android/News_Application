package com.application.newsappcompose.data.model

import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("status")
    val status: String,

    @SerializedName("sources")
    val sources: List<ApiSource>
)

