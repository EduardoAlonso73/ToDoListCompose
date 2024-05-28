package com.example.todolistapp.core.domain.util

import java.io.IOException


data class ResultEntity<T>(
    val data: T? = null,
    val error: ErrorEntity? = null
)


sealed interface ErrorEntity {

    data class ServiceError(val key: String, val message: String) : ErrorEntity
    data object EmptyBodyError : ErrorEntity
    data class UnknownError(val exception: Exception?) : ErrorEntity
}