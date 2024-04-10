package com.example.newsapp_task.data

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/v2/top-headlines?country=us&apiKey=7f1b61d4765149af8aad403c5d4bd320")

    suspend fun Get_News():Response<News>
}


