package com.example.todo.Models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.Services.ApiService
import com.example.todo.Types.IPosts
import kotlinx.coroutines.launch

class PostsModel: ViewModel() {
    private val _postList = mutableStateListOf<IPosts>()
    var errorMessage: String by mutableStateOf("")
    val postList: List<IPosts>
        get() = _postList

    val post = mutableStateOf<IPosts>(IPosts(id = 0, title = "", body = "", userId = 0))

    fun getPostList() {
        Log.d("PostModel", "inside")
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                Log.d("PostModel", "getPostList: inside try")
                _postList.clear()
                if (apiService != null) {
                    _postList.addAll(apiService.getPosts())
                }
                Log.d("PostModel", "getPostList: ${_postList.size}")

            } catch (e: Exception) {
                Log.d("PostModel", "getPostList: error ${e.message}")
                errorMessage = e.message.toString()
            }
        }
    }

    fun getPost() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                Log.d("PostModel", "getPost: inside try")

                if (apiService != null) {
                    post.value = apiService.getPost()
                }

            } catch (e: Exception) {
                Log.d("PostModel", "getPost: error ${e.message}")
                errorMessage = e.message.toString()
            }
        }
    }
}