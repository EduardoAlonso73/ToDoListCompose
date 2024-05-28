package com.example.todolistapp.core.data.mapper

import com.example.todolistapp.core.data.networking.endpoint.GetToDoListEndpoint
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity

fun List<GetToDoListEndpoint.ToDoDto>.toDoToEntity(): List<GetToDoEntity> {
    return this.map {
        GetToDoEntity(
            userId = it.userId ?: 0,
            id = it.id ?: 0,
            title = it.title.orEmpty(),
            completed = it.completed ?: false
        )
    }
}