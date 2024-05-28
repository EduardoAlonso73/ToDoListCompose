package com.example.todolistapp.core.di

import com.example.todolistapp.core.domain.repository.ToDoRepositoryBase
import com.example.todolistapp.core.domain.usecase.GetToDo.GetToDoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseDiModule {
    @Provides
    fun provideGetToDoUseCase(
        toDoRepository: ToDoRepositoryBase
    ): GetToDoUseCase = GetToDoUseCase(toDoRepository)

}