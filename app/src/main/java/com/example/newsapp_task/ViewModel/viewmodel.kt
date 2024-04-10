package com.example.newsapp_task.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_task.data.Api
import com.example.newsapp_task.data.my_data
import com.example.newsapp_task.data.news_data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class viewmodel @Inject constructor(private val api:Api):ViewModel() {



    fun GetFromServer(context:Context){

            viewModelScope.launch(Dispatchers.IO) {
                try {

                var response = api.Get_News()
                if (response.isSuccessful) {
                    response.body()!!.articles.forEach {
                        if (it.description == null || it.urlToImage == null || it.title == null || it.url == null) {

                        } else {
                            my_data.List_of_News.add(
                                news_data(
                                    it.title,
                                    it.description,
                                    it.urlToImage,
                                    it.url
                                )
                            )
                        }

                    }
                    my_data.state = true
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "The Load Failed from the Server.\n ReLoading ...",
                            Toast.LENGTH_LONG
                        ).show()
                        delay(4000)
                        GetFromServer(context)
                    }


                }
            }catch (e:Exception){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Please Connect To The Internet ..",
                            Toast.LENGTH_LONG
                        ).show()
                        delay(4000)
                        GetFromServer(context)
                    }

            }
            }


    }
}