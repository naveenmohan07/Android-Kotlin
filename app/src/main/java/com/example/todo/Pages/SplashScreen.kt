package com.example.todo.Pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.todo.R
import com.example.todo.utils.CommonUtil

class SplashScreen {
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun SplashWrapper(navController: NavHostController) {
       CommonUtil().autoNavigator(navController, "dashboard")

        Box(
           modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
        }
    }
}