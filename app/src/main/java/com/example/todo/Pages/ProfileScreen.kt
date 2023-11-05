package com.example.todo.Pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.todo.Models.PostsModel

class ProfileScreen {

        @Composable
    fun ProfileWrapper(sharedPostModel: PostsModel) {
            LaunchedEffect(Unit) {
                Log.d("ProfileWrapper", "ProfileWrapper: LAUNCHED")
                sharedPostModel.getPostList()
                sharedPostModel.getPost()
            }
            Column() {
                Text(text = "Total Available Posts: ${sharedPostModel.postList.size}")
                Text(text = "Single Post Title: ${sharedPostModel.post.value.title}")
            }
    }
}