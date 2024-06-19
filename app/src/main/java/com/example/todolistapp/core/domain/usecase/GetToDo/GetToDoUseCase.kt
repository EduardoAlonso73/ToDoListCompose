package com.example.todolistapp.core.domain.usecase.GetToDo

import com.example.todolistapp.core.domain.repository.ToDoRepositoryBase
import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.core.domain.util.ResultDataEntity

class GetToDoUseCase(private val toDoRepository: ToDoRepositoryBase) {
    suspend operator fun invoke(): ResultDataEntity<List<GetToDoEntity>,ErrorEntity.Network> {
        return toDoRepository.getToDoList()
    }

}

