package com.mradmin.yks13.kotlinweather.extension

import com.mradmin.yks13.kotlinweather.model.api.WeatherResponse
import io.reactivex.Single
import io.reactivex.disposables.Disposable

fun <T> Single<T>.subscribeAndHandleError(onNext: (it: T) -> Unit, onError: (error: String) -> Unit): Disposable =
        subscribe({
            onNext(it)
        }, {throwable ->
            val error = throwable.handleApiError<WeatherResponse>()
            onError(error)
        })