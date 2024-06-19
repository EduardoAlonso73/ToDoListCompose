package com.example.todolistapp.core.domain.repository

import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.core.domain.util.ResultDataEntity

interface ToDoRepositoryBase {
    suspend fun getToDoList(): ResultDataEntity<List<GetToDoEntity>, ErrorEntity.Network>
}