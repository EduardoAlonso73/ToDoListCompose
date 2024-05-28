package com.example.todolistapp.core.data.networking

import com.example.todolistapp.core.data.networking.endpoint.GetToDoListEndpoint
import com.example.todolistapp.core.data.networking.endpoint.toDoList
import retrofit2.Response
import retrofit2.http.GET

interface ToDoApi {
    @GET(GetToDoListEndpoint.URL)
    suspend fun fetchToDoList(): Response<toDoList>
}