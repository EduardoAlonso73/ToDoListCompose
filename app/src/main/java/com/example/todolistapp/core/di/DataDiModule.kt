package com.example.todolistapp.core.di

import com.example.todolistapp.core.data.networking.ToDoApi
import com.example.todolistapp.core.data.repository.ToDoRepository
import com.example.todolistapp.core.domain.repository.ToDoRepositoryBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {

    @Provides
    @Singleton
    fun provideToDoApi(): ToDoApi {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ToDoApi::class.java)
    }


    @Provides
    fun provideToDoRepository(toDoApi: ToDoApi): ToDoRepositoryBase = ToDoRepository(toDoApi)

}