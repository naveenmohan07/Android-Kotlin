package com.example.todo.utils

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController

class CommonUtil {

    @Composable
    fun autoNavigator(navController: NavHostController, route: String) {
        DisposableEffect(Unit) {
            val handler = Handler(Looper.getMainLooper())
            val delayMillis: Long = 1000

            handler.postDelayed({
                navController.navigate(route)
            }, delayMillis)

            onDispose {
                handler.removeCallbacksAndMessages(null)
            }
        }
    }
}