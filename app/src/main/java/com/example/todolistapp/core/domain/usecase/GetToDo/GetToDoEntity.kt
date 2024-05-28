package com.example.todolistapp.core.domain.usecase.GetToDo

data class GetToDoEntity(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
