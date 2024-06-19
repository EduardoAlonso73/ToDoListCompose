package com.example.todolistapp.core.domain.util

import com.example.todolistapp.core.domain.util.ResultDataEntity.State.*


data class ResultDataEntity<out T, out E : Error>(
    val state: State,
    val data: T? = null,
    val error: E? = null
) {
    enum class State {
        SUCCESS, ERROR
    }

    companion object {
        fun <T, E : Error> success(data: T): ResultDataEntity<T, E> {
            return ResultDataEntity(SUCCESS, data, error = null)
        }

        fun <T, E : Error> error(error: E?): ResultDataEntity<T, E> {
            return ResultDataEntity(ERROR, null, error)
        }
    }
}
inline fun <T, E : Error> ResultDataEntity<T, E>.getResult(
    onSuccess: (T?) -> Unit,
    onError: (E?) -> Unit
) {
    when (this.state) {
        SUCCESS -> onSuccess(this.data)
        ERROR -> onError(this.error)
    }
}

sealed interface Error
sealed interface ErrorEntity : Error {

    sealed interface Network : ErrorEntity {
        data object RequestTimeout : Network
        data class ServiceError(val key: String, val message: String) : Network
        data object EmptyBodyError : Network
        data class UnknownError(val exception: Exception?) : Network
    }

    sealed interface Local : ErrorEntity {
        data object EmptyBodyError : Network
        data class UnknownError(val exception: Exception?) : Network
    }
}