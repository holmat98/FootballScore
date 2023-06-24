package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

internal data class ArticlesApi(
    @SerializedName("articles")
    val articles: List<ArticleApi>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
