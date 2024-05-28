package com.example.todolistapp.core.domain.usecase.GetToDo

import com.example.todolistapp.core.domain.repository.ToDoRepositoryBase
import com.example.todolistapp.core.domain.util.ResultEntity

class GetToDoUseCase(private val toDoRepository: ToDoRepositoryBase) {
    suspend operator fun invoke(): ResultEntity<List<GetToDoEntity>> {
        return toDoRepository.getToDoList()
    }

}

