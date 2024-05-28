package com.example.todolistapp.core.data.repository

import com.example.todolistapp.core.data.mapper.toDoToEntity
import com.example.todolistapp.core.data.networking.ToDoApi
import com.example.todolistapp.core.data.networking.util.BaseRepository
import com.example.todolistapp.core.domain.repository.ToDoRepositoryBase
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.core.domain.util.ResultEntity

class ToDoRepository(private val toDoApi: ToDoApi) : ToDoRepositoryBase, BaseRepository() {
    override suspend fun getToDoList(): ResultEntity<List<GetToDoEntity>> {
        return safeApiCall(apiToBeCalled = { toDoApi.fetchToDoList() }) {
            return@safeApiCall ResultEntity(data = it.toDoToEntity())

        }
    }
}