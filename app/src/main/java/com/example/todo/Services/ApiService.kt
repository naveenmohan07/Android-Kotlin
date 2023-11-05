package com.example.todo.Services

import com.example.todo.Types.IPosts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<IPosts>

    @GET("posts/1")
    suspend fun getPost(): IPosts

    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService? {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService
        }
    }
}