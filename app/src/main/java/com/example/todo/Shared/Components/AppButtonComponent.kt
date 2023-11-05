package com.example.todo.Shared.Components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class AppButtonComponent {
    @Composable
    fun AppButton(buttonText: String, buttonCallback:(String) -> Unit, isFullWidth: Boolean) {
        Button(
            shape = RoundedCornerShape(14),
            modifier = when (isFullWidth) {
                true -> {Modifier.fillMaxWidth()}
                else -> {Modifier}
            },
            onClick = {
                Log.d("AppButton", "AppButton: Clicked")
                buttonCallback("hai")
            }
        ) {
            Text(text = buttonText)
        }
    }
}