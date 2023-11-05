package com.example.todo.Components.Dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.ui.theme.BoldFont
import com.example.todo.ui.theme.RegularFont
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class Activity {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ActivityContainer() {
        val scrollState = rememberScrollState()

        val calendar = Calendar.getInstance()

        val startDate = LocalDate.now().toString().split("-").last().toInt()
        val endDate: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        Column(
            modifier = Modifier.padding(10.dp).background(color = Color.White)
        ) {
            Row(
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Activities",style = TextStyle(
                    fontFamily = BoldFont,
                    fontSize = 18.sp
                )
                )
                Text(text = "Add activity",style = TextStyle(
                    fontFamily = BoldFont,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue

                )
                )
            }
            Row(
                modifier = Modifier.horizontalScroll(scrollState)
            ) {
                for (i in startDate ..endDate) {
                    DateCard(i)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateCardColor(currentDate: LocalDate, cardDate: Int): Color {
        var currentDate = currentDate.toString().split("-").last().toInt()
        return if(currentDate == cardDate) Color(0xFF1b3049)  else Color(0xFFDDDEE1)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateCardSize(currentDate: LocalDate, cardDate: Int): Color {
        var currentDate = currentDate.toString().split("-").last().toInt()
        return if(currentDate == cardDate) Color(0xFF1b3049)  else Color(0xFFDDDEE1)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun DateCard(date: Int) {
        val currentDate = LocalDate.now()
        val day = LocalDate.of(currentDate.year, currentDate.month, date)
        Box(
            modifier= Modifier
                .size(100.dp, 110.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(getDateCardColor(currentDate ,date)),
            contentAlignment = Alignment.Center

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = day.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault()), style = TextStyle(
                    fontFamily = RegularFont,
                    color = Color.White,
//                    fontSize = 16.sp
                )
                )
                Text(text = date.toString(), style = TextStyle(
                    fontFamily = BoldFont,
                    color = Color.White,
                    fontSize = 22.sp
                )
                )
            }
        }
    }
}