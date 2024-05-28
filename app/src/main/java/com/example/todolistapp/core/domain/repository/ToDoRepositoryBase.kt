package com.example.todolistapp.core.domain.repository

import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.core.domain.util.ResultEntity

interface ToDoRepositoryBase {
    suspend fun getToDoList(): ResultEntity<List<GetToDoEntity>>
}