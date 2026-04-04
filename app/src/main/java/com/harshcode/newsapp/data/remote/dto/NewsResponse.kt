package com.harshcode.newsapp.data.remote.dto

import com.harshcode.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)