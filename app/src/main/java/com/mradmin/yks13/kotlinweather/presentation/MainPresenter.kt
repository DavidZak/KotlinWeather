package com.mradmin.yks13.kotlinweather.presentation

import android.location.Location
import android.util.Log
import com.mradmin.yks13.kotlinweather.base.BasePresenter
import com.mradmin.yks13.kotlinweather.base.Service
import com.mradmin.yks13.kotlinweather.extension.subscribeAndHandleError
import com.mradmin.yks13.kotlinweather.service.GeolocationService
import com.mradmin.yks13.kotlinweather.service.GeolocationServiceListener
import com.mradmin.yks13.kotlinweather.service.WeatherService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainPresenter(mainView: MainView): BasePresenter<MainView>(mainView), GeolocationServiceListener {

    private val subscriptions = CompositeDisposable()
    private val geolocationService = Service.create<GeolocationService>()
    private val weatherService = Service.create<WeatherService>()

    private val currentLocation: BehaviorSubject<Location> = BehaviorSubject.create()

    override fun onLocation(location: Location) {

        if (currentLocation.value != null) {
            view.hideLoading()
            return
        }

        currentLocation.onNext(location)
    }

    private fun getWeatherForCoordinate(location: Location) {
        view.showLoading()

        val localeName = geolocationService.getLocationLocale(view.getContext(), location.latitude, location.longitude)

        val subscribe = weatherService.getWeatherForCoordinate(location.latitude.toString(), location.longitude.toString())
                .doAfterTerminate {
                    view.hideLoading()
                }
                .subscribeAndHandleError({
                    it.cityName = localeName
                    view.getWeather(it)
                }, {
                    //view.hideLoading()
                    view.showError(it)
                })

        subscriptions.add(subscribe)
    }

    fun getCurrentLocation() {
        view.showLoading()
        geolocationService.getCurrentLocation(view.getContext(), this)
    }

    fun onViewRefresh() {
        if (currentLocation.value == null)
            return

        getWeatherForCoordinate(currentLocation.value!!)
    }

    override fun onViewCreated() {

        val disposable = currentLocation.subscribe ({
            getWeatherForCoordinate(it)
        }, {
            view.showError(it.localizedMessage)
        })

        subscriptions.add(disposable)

    }

    override fun onViewDestroyed() {
        subscriptions.dispose()
        geolocationService.stopLocationUpdates(view.getContext())
    }
}