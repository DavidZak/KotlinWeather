package com.mradmin.yks13.kotlinweather.model.entity.daily

import com.mradmin.yks13.kotlinweather.model.api.daily.Daily
import com.mradmin.yks13.kotlinweather.model.api.daily.Data

data class DailyEntity(
        val summary: String, //Mixed precipitation tomorrow through Tuesday, with temperatures falling to 25°F next Thursday.
        val icon: String, //snow
        val data: List<DataEntity>
)

data class DataEntity(
        val time: Int, //1515646800
        val summary: String, //Foggy starting in the evening.
        val icon: String, //fog
        val sunriseTime: Int, //1515672812
        val sunsetTime: Int, //1515706377
        val moonPhase: Double, //0.84
        val precipIntensity: Double, //0.0003
        val precipIntensityMax: Double, //0.0016
        val precipIntensityMaxTime: Int, //1515682800
        val precipProbability: Double, //0.13
        val precipType: String?, //rain
        val temperatureHigh: Double, //51.85
        val temperatureHighTime: Int, //1515700800
        val temperatureLow: Double, //45.7
        val temperatureLowTime: Int, //1515718800
        val apparentTemperatureHigh: Double, //51.85
        val apparentTemperatureHighTime: Int, //1515700800
        val apparentTemperatureLow: Double, //40.73
        val apparentTemperatureLowTime: Int, //1515718800
        val dewPoint: Double, //38.51
        val humidity: Double, //0.82
        val pressure: Double, //1027.19
        val windSpeed: Double, //6.71
        val windGust: Double, //29.91
        val windGustTime: Int, //1515729600
        val windBearing: Int, //210
        val cloudCover: Double, //0.59
        val uvIndex: Int, //1
        val uvIndexTime: Int, //1515679200
        val visibility: Double, //7.21
        val ozone: Double, //279.56
        val temperatureMin: Double, //33.82
        val temperatureMinTime: Int, //1515646800
        val temperatureMax: Double, //51.85
        val temperatureMaxTime: Int, //1515700800
        val apparentTemperatureMin: Double, //28.24
        val apparentTemperatureMinTime: Int, //1515646800
        val apparentTemperatureMax: Double, //51.85
        val apparentTemperatureMaxTime: Int //1515700800
)

fun Data.toDailyDataEntity(): DataEntity {
    return DataEntity(
            this.time,
            this.summary,
            this.icon,
            this.sunriseTime,
            this.sunsetTime,
            this.moonPhase,
            this.precipIntensity,
            this.precipIntensityMax,
            this.precipIntensityMaxTime,
            this.precipProbability,
            this.precipType,
            this.temperatureHigh,
            this.temperatureHighTime,
            this.temperatureLow,
            this.temperatureLowTime,
            this.apparentTemperatureHigh,
            this.apparentTemperatureHighTime,
            this.apparentTemperatureLow,
            this.apparentTemperatureLowTime,
            this.dewPoint,
            this.humidity,
            this.pressure,
            this.windSpeed,
            this.windGust,
            this.windGustTime,
            this.windBearing,
            this.cloudCover,
            this.uvIndex,
            this.uvIndexTime,
            this.visibility,
            this.ozone,
            this.temperatureMin,
            this.temperatureMinTime,
            this.temperatureMax,
            this.temperatureMaxTime,
            this.apparentTemperatureMin,
            this.apparentTemperatureMinTime,
            this.apparentTemperatureMax,
            this.apparentTemperatureMaxTime
    )
}

fun Daily.toDailyEntity(): DailyEntity {
    return DailyEntity(
            this.summary,
            this.icon,
            this.data.map { it.toDailyDataEntity() }
    )
}