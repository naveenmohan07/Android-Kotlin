package com.example.todo.Shared.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo.Types.MenuItem

class BottomNavigationComponent {

    private var bottomNavItems = listOf<MenuItem>(
        MenuItem(name = "Home", Icons.Default.Home, "wrapper"),
        MenuItem(name = "Second", Icons.Default.Menu, "profile")
    )


    @Composable
    fun BottomNavigationItems(
        navItem: MenuItem,
        navController: NavHostController
    ) {
        Column(
            modifier = Modifier.clickable {
                navController.navigate(navItem.navigationRoute)
            }
        ) {
            Icon(imageVector = navItem.icon, contentDescription = navItem.name)
        }
    }


    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        Box(modifier = Modifier
            .fillMaxWidth()

            .height(60.dp)

            .background(Color.White)) {

        }
        LazyRow(
            Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            items(bottomNavItems) { item ->
                BottomNavigationItems(item, navController)
            }
        }
    }
}