package com.example.todo.Components.Dashboard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.Models.TodoModel
import com.example.todo.Types.CategoryCard
import com.example.todo.ui.theme.BoldFont

class Category {


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun CategoryContainer(todoModel: TodoModel) {

        var categoryCardItems = remember {
            mutableStateOf(
                listOf<CategoryCard>(
                    CategoryCard(categoryIcon = Icons.Default.ShoppingCart, categoryName = "Personal To-do", pendingTask = 12),
                    CategoryCard(categoryIcon = Icons.Default.AccountBox, categoryName = "Work  To-do", pendingTask =442)
                )
            )
        }

        LaunchedEffect(Unit) {

            categoryCardItems.value = categoryCardItems.value.map {

                category -> when(category.categoryName) {

                    "Personal To-do" -> {
                        Log.d("Category", "${category.categoryName}")
                        category.copy(pendingTask = todoModel.todoList.count(){it.todoType == "personal"})
                    }
                    "Work  To-do" -> {
                        category.copy(pendingTask = todoModel.todoList.count(){it.todoType == "work"})
                    }
                else -> category
                }
            }

        }
        Column(
            modifier = Modifier.padding(10.dp).background(color = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Category", style = TextStyle(
                        fontFamily = BoldFont,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = "See all", style = TextStyle(
                        fontFamily = BoldFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue

                    )
                )
            }
            LazyRow(
                content = {
                    items(categoryCardItems.value) {item ->
                        CategoryCard(categoryName = item.categoryName, pendingTasks = item.pendingTask, categoryIcon = item.categoryIcon)
                    }
                },
            )
        }
    }


    @Composable
    fun CategoryCard(categoryName: String, pendingTasks: Int, categoryIcon: ImageVector) {
        
            Box(
                modifier= Modifier
                    .size(200.dp, 160.dp)
                    .padding(10.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = MaterialTheme.shapes.medium,
                        ambientColor = Color.Black,
                    )
                    .clip(MaterialTheme.shapes.medium)

                    .background(Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = categoryName,
                            modifier = Modifier.width(110.dp),
                            maxLines = 2,
                            style = TextStyle(
                                fontFamily = BoldFont,
                                fontSize = 16.sp,

                                fontWeight = FontWeight.Bold,
                            )
                        )
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0XFFFC466B),
                                            Color(0XFF3F5EFB)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector =  categoryIcon,
                                tint = Color.White,
                                contentDescription = "user",
                            )
                        }
                    }
                    Text(
                        text = "$pendingTasks Tasks remaining",
                        style = TextStyle(
                            fontFamily = BoldFont,
                            fontSize = 12.sp,
                            color = Color(0xFF86878E),
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Go to Tasks",
                            style = TextStyle(
                                fontFamily = BoldFont,
                                fontSize = 12.sp,
                                color = Color.Blue
                            )
                        )
                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow right", tint =  Color.Blue)
                    }
                }
            }
            
        


    }
}