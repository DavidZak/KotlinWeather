package com.mradmin.yks13.kotlinweather.model.entity

import com.mradmin.yks13.kotlinweather.model.api.Currently
import com.mradmin.yks13.kotlinweather.model.api.WeatherResponse
import com.mradmin.yks13.kotlinweather.model.entity.daily.DailyEntity
import com.mradmin.yks13.kotlinweather.model.entity.daily.toDailyEntity
import com.mradmin.yks13.kotlinweather.model.entity.hourly.HourlyEntity
import com.mradmin.yks13.kotlinweather.model.entity.hourly.toHourlyEntity
import com.mradmin.yks13.kotlinweather.model.entity.minutely.MinutelyEntity
import com.mradmin.yks13.kotlinweather.model.entity.minutely.toMinutelyEntity

data class WeatherEntity(
        val latitude: Double, //42.3601
        val longitude: Double, //-71.0589
        val timezone: String, //America/New_York
        val currently: CurrentlyEntity?,
        val minutely: MinutelyEntity?,
        val hourly: HourlyEntity?,
        val daily: DailyEntity?,
        val offset: Double, //-5,
        var cityName: String?
)

data class CurrentlyEntity(
        val time: Int, //1515707975
        val summary: String, //Mostly Cloudy
        val icon: String, //partly-cloudy-night
        val nearestStormDistance: Int, //181
        val nearestStormBearing: Int, //315
        val precipIntensity: Double, //0
        val precipProbability: Double, //0
        val temperature: Double, //50.65
        val apparentTemperature: Double, //50.65
        val dewPoint: Double, //44.9
        val humidity: Double, //0.81
        val pressure: Double, //1026.14
        val windSpeed: Double, //6.12
        val windGust: Double, //11.09
        val windBearing: Int, //211
        val cloudCover: Double, //0.63
        val uvIndex: Int, //0
        val visibility: Double, //7.52
        val ozone: Double //273.79
)

fun Currently.toCurrentlyEntity(): CurrentlyEntity {
    return CurrentlyEntity(
            this.time,
            this.summary,
            this.icon,
            this.nearestStormDistance,
            this.nearestStormBearing,
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

fun WeatherResponse.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
            this.latitude,
            this.longitude,
            this.timezone,
            if (this.currently != null) this.currently.toCurrentlyEntity() else null,
            if (this.minutely != null) this.minutely.toMinutelyEntity() else null,
            if (this.hourly != null) this.hourly.toHourlyEntity() else null,
            if (this.daily != null) this.daily.toDailyEntity() else null,
            this.offset,
            null)
}