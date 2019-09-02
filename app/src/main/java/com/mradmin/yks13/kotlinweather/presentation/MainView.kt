package com.mradmin.yks13.kotlinweather.presentation

import androidx.annotation.StringRes
import com.mradmin.yks13.kotlinweather.base.BaseView
import com.mradmin.yks13.kotlinweather.model.api.WeatherResponse
import com.mradmin.yks13.kotlinweather.model.entity.WeatherEntity

interface MainView: BaseView {
    /**
     * Updates the previous posts by the specified ones
     * @param posts the list of posts that will replace existing ones
     */
    fun getWeather(weather: WeatherEntity)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}