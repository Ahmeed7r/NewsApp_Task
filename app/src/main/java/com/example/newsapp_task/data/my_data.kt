package com.example.newsapp_task.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object my_data {

    var List_of_News:MutableList<news_data> by
        mutableStateOf(mutableListOf())

    var state by
        mutableStateOf(false)

}