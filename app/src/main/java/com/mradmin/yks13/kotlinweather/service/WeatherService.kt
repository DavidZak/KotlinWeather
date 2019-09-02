package com.mradmin.yks13.kotlinweather.service

import com.mradmin.yks13.kotlinweather.base.Service
import com.mradmin.yks13.kotlinweather.model.api.WeatherResponse
import com.mradmin.yks13.kotlinweather.model.entity.WeatherEntity
import com.mradmin.yks13.kotlinweather.model.entity.toWeatherEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherService: Service() {

    fun getWeatherForCoordinate(latitude: String, longitude: String): Single<WeatherEntity> {
        return apiInterface.getWeatherForCoordinate(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { weatherResponse: WeatherResponse ->
                    weatherResponse.toWeatherEntity()
                }
                .retry(1)
    }
}