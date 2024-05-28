package com.example.todolistapp.core.data.networking.endpoint

import com.google.gson.annotations.SerializedName

typealias toDoList = List<GetToDoListEndpoint.ToDoDto>

object GetToDoListEndpoint {
    data class ToDoDto(
        @SerializedName("userId") val userId: Int?,
        @SerializedName("id") val id: Int?,
        @SerializedName("title") val title: String?,
        @SerializedName("completed") val completed: Boolean
    )

    const val URL = "todos"


}