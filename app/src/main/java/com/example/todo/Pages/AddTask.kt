package com.example.todo.Pages

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo.Models.TodoModel
import com.example.todo.Shared.Components.AppButtonComponent
import com.example.todo.Types.TodoItems
import java.time.LocalDate
import java.util.Calendar

class AddTask {


    private val taskName = mutableStateOf("")
    private val taskError = "Task name is not valid"

    private val radioSelectorValue = mutableListOf<String>("Personal", "Work", "Family", "Friends")

    private val taskCategory = mutableStateOf("Personal")

    @RequiresApi(Build.VERSION_CODES.O)
    private val taskDate = mutableStateOf(LocalDate.now().toString())

    private val taskDescription = mutableStateOf("")

    private var dateFormat = "yyyy-MM-dd"

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddTaskWrapper(navController: NavHostController, sharedViewModel: TodoModel) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            topBar = {
                     TopAppBar(
                         colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                             containerColor = Color.White
                         ),
                         modifier = Modifier
                             .fillMaxWidth()
                             .background(color = Color.White),
                         navigationIcon = {
                              IconButton(
                                  onClick = {
                                      navController.popBackStack()
                                  }
                              ) {
                                  Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "back")
                              }
                         },
                         title = {
                             Text(text = "Create New Task")
                         }
                     )
            },
            content = { innerPadding -> 
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(innerPadding)
                ) {
                    formBuilder(navController, sharedViewModel)
                }
            }
        )
        
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun formBuilder(navController: NavHostController, sharedViewModel: TodoModel) {
        val textBoxColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0XFFD9D9D9),
            unfocusedBorderColor = Color(0XFFD9D9D9),
            disabledBorderColor = Color(0XFFF2F2F2),
            disabledTextColor = Color(0XFFF2F2F2),
        )

        val context = LocalContext.current
        Column(Modifier.padding(10.dp)) {
            QuestionNameBuilder(questionName = "Task name")
            OutlinedTextField(
                value = taskName.value,
                maxLines = 1,
                interactionSource = MutableInteractionSource(),
                isError = !validateUserName(),
                onValueChange = {
                    taskName.value = it;
                    Log.d("TAG", "formBuilder: $it")
                },
                placeholder = {
                              Text(
                                  text = "Task Name",
                                  style = TextStyle(
                                      color = Color(0XFF919191),
                                  )
                              )
                },
                colors = textBoxColors
            )
            if(!validateUserName()) {
                Text(
                    text = "ERROR $taskError",
                    color = Color.Red,
                )
            }
            QuestionNameBuilder(questionName = "Category")
            RadioSelector()
            QuestionNameBuilder(questionName = "Task Date")
            OutlinedTextField(
                value = taskDate.value,
                colors = textBoxColors,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            showDatePickerDialog(context)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "date")
                    }
                },
                onValueChange = {}
            )
            QuestionNameBuilder(questionName = "Description")
            OutlinedTextField(
                value = taskDescription.value,
                colors = textBoxColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                singleLine = false,
                maxLines = 5,
                placeholder = {
                    Text(
                        text = "Description",
                        style = TextStyle(
                            color = Color(0XFF919191),
                        )
                    )
                },
                onValueChange = {
                    taskDescription.value = it
                },
            )
            AppButtonComponent().AppButton(
                buttonText = "Submit",
                buttonCallback = {

                    val newTodo = TodoItems(description = taskDescription.value, taskDate = LocalDate.now(), todoType = taskCategory.value, id = (0..100).random(), todoName = taskName.value)
                    sharedViewModel.addTodo(newTodo)
                    navController.popBackStack()
                },
                isFullWidth = true
            )
        }
    }

    private fun validateUserName(): Boolean{
        return if (taskName.value.isEmpty()) {
            return true
        } else {
            return taskName.value.isNotEmpty() && taskName.value.length > 4
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDatePickerDialog(context: Context) {
        val calendar = Calendar.getInstance()
        var dateOfBirth: String = ""
         DatePickerDialog(
            context, { _, year, month, day ->
                taskDate.value = getPickedDateAsString(year, month, day)
                 Log.d("TAG", "formBuilder: $dateOfBirth")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
             .show()

    }


    private fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        Log.d("TAG", "formBuilder: ${dateFormat.format(calendar.time)}")
        return dateFormat.format(calendar.time)
    }



    @SuppressLint("RememberReturnType")
    @Composable
    fun RadioSelector() {
        val selectedIndex = remember {
            mutableStateOf(0)
        }
        Row() {
         for (radioValue in radioSelectorValue.indices) {
             Box(
                 Modifier
                     .padding(10.dp)
                     .clip(RoundedCornerShape(8.dp))
                     .background(
                         color = when (radioValue) {
                             selectedIndex.value -> {
                                 Color(0XFFB3D5F2).copy(alpha = 0.9f)
                             }

                             else -> {
                                 Color(0XFFB3D5F2).copy(alpha = 0.2f)
                             }
                         }
                     )
                     .padding(8.dp)
                     .clickable(
                         indication = null,
                         interactionSource = remember { MutableInteractionSource() }
                     ) {
                         selectedIndex.value = radioValue;
                         taskCategory.value = radioSelectorValue[radioValue]
                         Log.d("RADIO BUTTON", "RadioSelector: ${radioSelectorValue[radioValue]}")
                     }
             ) {
                 Text(text = radioSelectorValue[radioValue])
             }
         }
        }
    }

    @Composable
    fun QuestionNameBuilder(questionName: String) {
        Text(text = questionName,
            Modifier.padding(vertical = 8.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
    }
}