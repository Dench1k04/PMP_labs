package com.example.lab10

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int) : Response<User>

    @POST("/users/")
    suspend fun addUser(@Body user: User) : Response<UserResponse>
}