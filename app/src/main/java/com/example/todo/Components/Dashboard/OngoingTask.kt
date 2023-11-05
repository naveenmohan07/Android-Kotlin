package com.example.todo.Components.Dashboard

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todo.Models.TodoModel
import com.example.todo.R
import com.example.todo.Types.TodoItems
import com.example.todo.ui.theme.BoldFont

class OngoingTask {


    @SuppressLint("StateFlowValueCalledInComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun OngoingContainer(navController: NavHostController, viewModel: TodoModel = viewModel()) {

        Log.d("ADD_TASK", "OngoingContainer: ${viewModel.todoList.size}")

        val dataList = viewModel.todoList

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today's Task", style = TextStyle(
                        fontFamily = BoldFont,
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = "View all", style = TextStyle(
                        fontFamily = BoldFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue

                    )
                )
            }
                LazyColumn(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        if (dataList.isEmpty()) {
                            Text(text = "No Tasks available today")
                        }
                    }
                    items(dataList) { todo ->
                        OngoingTaskCard(todo)
                    }
                }
        }
    }


    @Composable
    fun OngoingTaskCard(todo: TodoItems) {
        val isClicked = remember {
            mutableStateOf(false)
        }
        Box(
            modifier= Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = MaterialTheme.shapes.medium,
                    ambientColor = Color.Black,
                )
                .clip(MaterialTheme.shapes.medium)
                .background(color = Color.White, shape = RoundedCornerShape(2))
                .border(
                    border = BorderStroke(2.dp, color = Color.White),
                    shape = RoundedCornerShape(12)
                )
                .clickable {
                    isClicked.value = true
                    Log.d("TAG", "OngoingTaskCard: ")
                }

        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                ) {
                    Image(painter = painterResource(id = R.drawable.work), contentDescription = "")
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = todo.todoName,
                            style = TextStyle(
                                fontFamily = BoldFont,
                                fontSize = 12.sp,
                                color = Color(0xFF3D3D3D),
                            )
                        )
                        Text(
                            text = todo.taskDate.toString(),
                            style = TextStyle(
                                fontFamily = BoldFont,
                                fontSize = 12.sp,
                                color = Color(0xFF3D3D3D),
                            )
                        )
                    }
                }
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "d", Modifier.clickable {
                    isClicked.value = true
                    Log.d("TAG", "OngoingTaskCard: ")
                })
                OpenPopup(isClicked, todo)
            }
        }
    }

    @Composable
    fun OpenPopup(isClicked: MutableState<Boolean>, currentTodo: TodoItems) {
    if(isClicked.value)
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnClickOutside = true, dismissOnBackPress = true),
            onDismissRequest = {
                isClicked.value = false
            }
        ){
            Surface(color = Color.White, modifier = Modifier.padding(horizontal = 20.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row() {
                        Image(painter = painterResource(id = R.drawable.work), contentDescription = "", modifier = Modifier.size(60.dp) ,contentScale = ContentScale.Fit)
                        Spacer(modifier = Modifier.size(20.dp))
                        Column() {
                            Text(text = "Task Name -> ${currentTodo.todoName}")
                            Text(text = currentTodo.taskDate.toString())
                        }

                    }
                    Divider(thickness = 1.dp, color = Color.Red, modifier = Modifier.padding(vertical = 12.dp))
                    Text(text = "Task Description ${currentTodo.description}")
                }

            }
        }
    }
}