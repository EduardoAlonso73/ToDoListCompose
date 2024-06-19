package com.example.todolistapp.core.data.networking.util

import com.example.todolistapp.core.domain.util.ErrorEntity
import com.example.todolistapp.core.domain.util.ResultDataEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {
    protected suspend inline fun <R, En> safeApiCall(
        crossinline apiToBeCalled: suspend () -> Response<R>,
        crossinline bodyHandler: (R) -> ResultDataEntity<En, ErrorEntity.Network>
    ): ResultDataEntity<En, ErrorEntity.Network> = withContext(IO) {
        return@withContext try {
            val response = apiToBeCalled()
            if (!response.isSuccessful) {
                return@withContext ResultDataEntity.error(
                    error = ErrorEntity.Network.ServiceError(
                        response.code().toString(),
                        response.message()
                    )
                )
            } else {
                val body = response.body() ?: return@withContext ResultDataEntity.error(
                    error = ErrorEntity.Network.EmptyBodyError,
                )
                return@withContext bodyHandler(body)
            }
        } catch (throwable: Exception) {
            ResultDataEntity.error<En, ErrorEntity.Network>(
                error = ErrorEntity.Network.UnknownError(
                    throwable
                )
            )
        }
    }
}