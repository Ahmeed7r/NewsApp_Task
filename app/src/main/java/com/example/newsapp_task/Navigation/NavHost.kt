package com.example.newsapp_task.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_task.UI_.DetailsScreen
import com.example.newsapp_task.UI_.HomeScreen
import com.example.newsapp_task.UI_.Splash

@Composable
fun NavHost() {
    val navcontroller = rememberNavController()

    androidx.navigation.compose.NavHost(
        navController = navcontroller,
        startDestination = "splash"
    ) {
        composable("splash") {

            Splash(navcontroller = navcontroller)
        }
        composable("main") {
            HomeScreen(navcontroller)
        }
        composable("details/{title}/{description}") {
            val title = it.arguments?.getString("title")
            val description = it.arguments?.getString("description")

            val url = it.arguments?.getString("url")
            DetailsScreen(title.toString(),description.toString())
        }

    }

}