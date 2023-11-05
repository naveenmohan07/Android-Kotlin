package com.example.todo.Types

import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDate

data class MenuItem(val name: String, val icon: ImageVector, val navigationRoute: String)


data class CategoryCard(val categoryName: String, val categoryIcon: ImageVector, val pendingTask: Int)


data class TodoItems(val id: Int, val todoName: String, val todoType: String, val taskDate: LocalDate, val description: String)