package com.mradmin.yks13.kotlinweather.model.entity.hourly

import com.mradmin.yks13.kotlinweather.model.api.hourly.Data
import com.mradmin.yks13.kotlinweather.model.api.hourly.Hourly


data class HourlyEntity(
        val summary: String, //Windy starting tomorrow morning and rain tomorrow afternoon.
        val icon: String, //rain
        val data: List<DataEntity>
)

data class DataEntity(
        val time: Int, //1515704400
        val summary: String, //Partly Cloudy
        val icon: String, //partly-cloudy-day
        val precipIntensity: Double, //0
        val precipProbability: Double, //0
        val temperature: Double, //51.53
        val apparentTemperature: Double, //51.53
        val dewPoint: Double, //45.36
        val humidity: Double, //0.79
        val pressure: Double, //1025.88
        val windSpeed: Double, //5.48
        val windGust: Double, //8.73
        val windBearing: Int, //214
        val cloudCover: Double, //0.53
        val uvIndex: Int, //0
        val visibility: Double, //8.89
        val ozone: Double //274.2
)

fun Data.toHourlyDataEntity(): DataEntity {
    return DataEntity(
            this.time,
            this.summary,
            this.icon,
            this.precipIntensity,
            this.precipProbability,
            this.temperature,
            this.apparentTemperature,
            this.dewPoint,
            this.humidity,
            this.pressure,
            this.windSpeed,
            this.windGust,
            this.windBearing,
            this.cloudCover,
            this.uvIndex,
            this.visibility,
            this.ozone
    )
}

fun Hourly.toHourlyEntity(): HourlyEntity {
    return HourlyEntity(
            this.summary,
            this.icon,
            this.data.map { it.toHourlyDataEntity() }
    )
}