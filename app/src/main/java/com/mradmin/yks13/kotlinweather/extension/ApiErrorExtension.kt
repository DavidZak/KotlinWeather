package com.mradmin.yks13.kotlinweather.extension

import com.google.gson.GsonBuilder
import com.mradmin.yks13.kotlinweather.model.api.BaseResponse
import retrofit2.HttpException

inline fun <reified T: BaseResponse> Throwable.handleApiError(): String {
    if (this is HttpException && this.response() != null && this.response()!!.errorBody() != null) {
        val body = this.response()!!.errorBody()!!.string()
        val result = GsonBuilder().create().fromJson<T>(body, T::class.java)
        return result.error
    }
    return this.localizedMessage
}