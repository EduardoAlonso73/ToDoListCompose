package com.example.todolistapp.core.data.repository

import com.example.todolistapp.core.data.mapper.toDoToEntity
import com.example.todolistapp.core.data.networking.ToDoApi
import com.example.todolistapp.core.data.networking.util.BaseRepository
import com.example.todolistapp.core.domain.repository.ToDoRepositoryBase
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoEntity
import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.core.domain.util.ResultDataEntity


class ToDoRepository(private val toDoApi: ToDoApi) : ToDoRepositoryBase, BaseRepository() {
    override suspend fun getToDoList(): ResultDataEntity<List<GetToDoEntity>, ErrorEntity.Network> {
        return safeApiCall(apiToBeCalled = { toDoApi.fetchToDoList() }) {
            return@safeApiCall ResultDataEntity.success(data = it.toDoToEntity())

        }
    }


}