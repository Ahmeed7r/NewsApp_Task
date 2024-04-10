package com.example.newsapp_task.UI_

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.io.File

@Composable
fun DetailsScreen(title:String,description:String){
    var context = LocalContext.current.applicationContext
    var handler_url= LocalUriHandler.current

    //for  url
    var url_path=context.getExternalFilesDir(null)!!.absolutePath
    val tempfile_url = File(url_path, "url")
    var url=tempfile_url.readText()

    File("$url_path/url").deleteOnExit()
    Column(modifier = Modifier.fillMaxSize().padding(10.dp).clickable {

                    handler_url.openUri(url)




    }, horizontalAlignment = Alignment.CenterHorizontally) {


        //for image url
        var image_path=context.getExternalFilesDir(null)!!.absolutePath
        val tempfile_image = File(image_path, "image")
        var image=tempfile_image.readText()
        File("$image_path/image").deleteOnExit()


        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image)
                .build(),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(6.dp)
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Black
            ,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Right
        )

        Text(
            text = description,
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