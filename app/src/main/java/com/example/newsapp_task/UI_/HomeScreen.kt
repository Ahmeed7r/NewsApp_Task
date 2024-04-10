package com.example.newsapp_task.UI_

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp_task.ViewModel.viewmodel
import com.example.newsapp_task.data.my_data
import com.example.newsapp_task.data.news_data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@Composable
fun HomeScreen(navcontroller: NavController){



   if(my_data.state){
       Show_News(list = my_data.List_of_News, navController = navcontroller)
   }else{
       animations()
   }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun Show_News(list:List<news_data>,navController: NavController){
    var context= LocalContext.current.applicationContext


        LazyColumn() {

            items(list) {



                Box( modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .clickable {
                        val image_path = context.getExternalFilesDir(null)!!.absolutePath
                        val tempfile = File(image_path, "image")
                        tempfile.writeText(it.image)

                        val url_path = context.getExternalFilesDir(null)!!.absolutePath
                        val url_temp = File(url_path, "url")
                        url_temp.writeText(it.url)
                        navController.navigate(
                            "details/${it.title}/${it.description}"
                        )

                    }
                    .background(Color.Gray.copy(alpha = 0.6f))
//                .shadow(4.dp, shape = RoundedCornerShape(15.dp))
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .padding(5.dp)
                            .background(Color(0xFFF4F4F4)),
                        horizontalAlignment = Alignment.CenterHorizontally) {


                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(it.image)
                                .build(),
                            contentDescription = "image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = it.title.toString(),
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                            ,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Right
                        )

                    }


            }

        }

    }





}



@Composable
fun animations() {
    val scolor = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.6f)
    )
    val transiction = rememberInfiniteTransition(label = "")
    val translate = transiction.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = FastOutSlowInEasing

            )
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = scolor,
        start = Offset.Zero,
        end = Offset(x = translate.value, y = translate.value)
    )
    Shimer(brush = brush)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Shimer(brush: Brush) {
    LazyColumn(){
        items(7){
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(300.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(15.dp)),
                    //elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    //  colors = CardDefaults.cardColors(containerColor = Color.White),


                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(270.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(brush)
                        )


                    }
                }
            }
        }
    }


}


