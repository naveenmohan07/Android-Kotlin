package com.example.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.Models.PostsModel
import com.example.todo.Models.TodoModel
import com.example.todo.Pages.AddTask
import com.example.todo.Pages.DashboardScreen
import com.example.todo.Pages.SplashScreen

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface() {
                val navController = rememberNavController()
                val sharedViewModel: TodoModel by viewModels()
                val sharedPostModel: PostsModel by viewModels()

                NavHost(
                    navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen().SplashWrapper(navController)
                    }
                    composable("dashboard") {
                        DashboardScreen().dash(navController = navController, todoModel = sharedViewModel, sharedPostModel)
                    }
                    composable("addTask") {
                        AddTask().AddTaskWrapper(navController = navController, sharedViewModel = sharedViewModel)
                    }
                }
            }
        }
    }
}
