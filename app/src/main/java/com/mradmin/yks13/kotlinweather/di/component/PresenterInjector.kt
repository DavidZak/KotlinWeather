package com.mradmin.yks13.kotlinweather.di.component

import com.mradmin.yks13.kotlinweather.base.BaseView
import com.mradmin.yks13.kotlinweather.di.module.ContextModule
import com.mradmin.yks13.kotlinweather.di.module.NetworkModule
import com.mradmin.yks13.kotlinweather.presentation.MainPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(mainPresenter: MainPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}