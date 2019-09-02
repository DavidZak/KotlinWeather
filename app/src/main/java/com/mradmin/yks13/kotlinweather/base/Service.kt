package com.mradmin.yks13.kotlinweather.base

import com.mradmin.yks13.kotlinweather.api.ApiInterface

open class Service {

    protected val apiInterface = ApiInterface.create()

    companion object Factory {
        inline fun <reified T: Service> create(): T {
            return T::class.java.newInstance()
        }
    }

}