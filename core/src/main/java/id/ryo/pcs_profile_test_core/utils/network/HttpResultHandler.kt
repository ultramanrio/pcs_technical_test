package id.ryo.pcs_profile_test_core.utils.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading)
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultWrapper.Success(it))
            } ?: emit(ResultWrapper.Error("Empty response body", response.code()))
        } else {
            emit(ResultWrapper.Error(response.message(), response.code())) // ✅ Emit Error
        }
    } catch (e: IOException) {
        emit(ResultWrapper.NetworkError)
    } catch (e: Exception) {
        emit(ResultWrapper.Error(e.localizedMessage ?: "Unknown error")) // ✅ Emit Generic Error
    }
}.flowOn(Dispatchers.IO)


inline fun <T> safeApiCallWithCaching(
    crossinline apiCall: suspend () -> Response<T>,
    crossinline saveToLocal: suspend (T) -> Unit,
    crossinline getFromLocal: suspend () -> T
): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading)

    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                saveToLocal(data)
                emit(ResultWrapper.Success(data))
            } ?: emit(ResultWrapper.Error("Empty response body", response.code()))
        } else {
            emit(ResultWrapper.Error(response.message(), response.code()))
        }
    } catch (e: IOException) {
        val cachedData = getFromLocal()
        if (cachedData != null) {
            emit(ResultWrapper.Success(cachedData))
        } else {
            emit(ResultWrapper.NetworkError)
        }
    } catch (e: Exception) {
        emit(ResultWrapper.Error(e.localizedMessage ?: "Unknown error"))
    }
}.flowOn(Dispatchers.IO)