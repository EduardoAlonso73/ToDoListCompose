package com.example.todolistapp.core.data.networking.util

import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.core.domain.util.ResultEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <R, E> safeApiCall(
        apiToBeCalled: suspend () -> Response<R>,
        bodyHandler: (R) -> ResultEntity<E>
    ): ResultEntity<E> = withContext(IO) {
        return@withContext try {
            val response = apiToBeCalled()
            if (!response.isSuccessful) {
                return@withContext ResultEntity(
                    error = ErrorEntity.ServiceError(response.code().toString(), response.message())
                )
            } else {
                val body = response.body() ?: return@withContext ResultEntity(
                    error = ErrorEntity.EmptyBodyError,
                )
                return@withContext bodyHandler(body)
            }
        } catch (throwable: Exception) {
            ResultEntity<E>(error = ErrorEntity.UnknownError(throwable))
        }
    }
}