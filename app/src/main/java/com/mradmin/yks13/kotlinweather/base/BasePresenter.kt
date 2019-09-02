package com.mradmin.yks13.kotlinweather.base

import com.mradmin.yks13.kotlinweather.di.component.DaggerPresenterInjector
import com.mradmin.yks13.kotlinweather.di.component.PresenterInjector
import com.mradmin.yks13.kotlinweather.di.module.ContextModule
import com.mradmin.yks13.kotlinweather.di.module.NetworkModule
import com.mradmin.yks13.kotlinweather.presentation.MainPresenter

abstract class BasePresenter <out V: BaseView>(protected val view: V) {

    /**
     * The injector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is create
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainPresenter -> injector.inject(this)
        }
    }
}