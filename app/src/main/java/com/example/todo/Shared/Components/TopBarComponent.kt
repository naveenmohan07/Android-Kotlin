package com.example.todo.Shared.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.ui.theme.BoldFont
import com.example.todo.ui.theme.SemiBoldFont
import java.util.Calendar

class TopBarComponent {

    private var todayTask: Int = 3

    private fun getGreetingMessage(): String {
        val calendar = Calendar.getInstance()
        return when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 6..11 -> "Morning"
            in 12..16 -> "Afternoon"
            in 17..23 -> "Evening"
            else -> "Morning"
        }
    }


    @Composable
    fun TopBarWrapper() {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row {
                    Text(
                        text = getGreetingMessage(), style = TextStyle(
                            fontFamily = BoldFont,
                            fontSize = 32.sp
                        )
                    )
                    Text(
                        text = ", Naveen", style = TextStyle(
                            fontFamily = BoldFont,
                            fontSize = 32.sp
                        )
                    )
                }
                Row(

                    modifier = Modifier.padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification",
                        Modifier.size(18.dp),
                        tint = Color(0xFFea6e7e)
                    )
                    Text(
                        text = "$todayTask tasks", style = TextStyle(
                            fontFamily = BoldFont,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFea6e7e)
                        )
                    )
                    Text(
                        text = " are waiting for you today", style = TextStyle(
                            fontFamily = SemiBoldFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF86878E),
                            textAlign = TextAlign.End,
                        )
                    )
                }
            }

        }
    }

}