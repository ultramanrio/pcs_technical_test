package id.ryo.pcs_profile_test_core.utils.network

sealed class ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(val message: String?, val code: Int? = null) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}