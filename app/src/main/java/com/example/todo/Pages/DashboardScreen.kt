package com.example.todo.Pages

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.Components.Dashboard.Activity
import com.example.todo.Components.Dashboard.Category
import com.example.todo.Components.Dashboard.OngoingTask
import com.example.todo.Models.PostsModel
import com.example.todo.Models.TodoModel
import com.example.todo.Shared.Components.BottomNavigationComponent
import com.example.todo.Shared.Components.TopBarComponent


class DashboardScreen {


    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun dash(
        navController: NavHostController,
        todoModel: TodoModel,
        sharedPostModel: PostsModel
    ) {
        val childNavController: NavHostController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar =  {
                    TopBarComponent().TopBarWrapper()
                },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            childNavController, startDestination = "wrapper"
                        ) {
                            composable("wrapper") {
                                DashboardWrapper(navController = childNavController, todoModel = todoModel)
                            }
                            composable("profile") {
                                ProfileScreen().ProfileWrapper(sharedPostModel)
                            }
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("addTask")
                        }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                },
                bottomBar = {
                    BottomNavigationComponent().BottomNavigationBar(childNavController)
                },
                floatingActionButtonPosition = FabPosition.Center,
            )

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun DashboardWrapper(navController: NavHostController, todoModel: TodoModel) {

        Column(
            modifier = Modifier.background(color = Color.White),
        ) {
            ContentWrapper(navController, todoModel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ContentWrapper(navController: NavHostController, todoModel: TodoModel) {
        Activity().ActivityContainer()
        Category().CategoryContainer(todoModel)
        OngoingTask().OngoingContainer(navController, todoModel)
        }
}