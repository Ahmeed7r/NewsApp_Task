package com.example.newsapp_task.data

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)