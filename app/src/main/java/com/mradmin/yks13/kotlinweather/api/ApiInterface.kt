package com.mradmin.yks13.kotlinweather.api

import com.mradmin.yks13.kotlinweather.model.api.WeatherResponse
import com.mradmin.yks13.kotlinweather.util.Constants
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{latitude},{longitude}")
    fun getWeatherForCoordinate(@Path("latitude") latitude: String,
                          @Path("longitude") longitude: String): Single<WeatherResponse>

    companion object Factory {
        fun create(): ApiInterface {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

            val retrofit = retrofit2.Retrofit.Builder()
                    .client(okhttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

}