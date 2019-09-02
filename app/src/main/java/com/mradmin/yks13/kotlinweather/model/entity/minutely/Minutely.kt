package com.mradmin.yks13.kotlinweather.model.entity.minutely

import com.mradmin.yks13.kotlinweather.model.api.minutely.Data
import com.mradmin.yks13.kotlinweather.model.api.minutely.Minutely

data class MinutelyEntity(
        val summary: String, //Mostly cloudy for the hour.
        val icon: String, //partly-cloudy-night
        val data: List<DataEntity>
)

data class DataEntity(
        val time: Int, //1515707940
        val precipIntensity: Double, //0
        val precipProbability: Double //0
)

fun Data.toMinutelyDataEntity(): DataEntity {
    return DataEntity(
            this.time,
            this.precipIntensity,
            this.precipProbability
    )
}

fun Minutely.toMinutelyEntity(): MinutelyEntity {
    return MinutelyEntity(
            this.summary,
            this.icon,
            this.data.map { it.toMinutelyDataEntity() }
    )
}