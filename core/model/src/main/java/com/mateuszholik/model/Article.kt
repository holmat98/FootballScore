package com.mateuszholik.model

import java.time.LocalDateTime

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: LocalDateTime,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
