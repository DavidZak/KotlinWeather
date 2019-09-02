package com.mradmin.yks13.kotlinweather.presentation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mradmin.yks13.kotlinweather.R
import com.mradmin.yks13.kotlinweather.base.BaseActivity
import com.mradmin.yks13.kotlinweather.model.entity.WeatherEntity
import com.mradmin.yks13.kotlinweather.util.DateUtils
import com.mradmin.yks13.kotlinweather.util.PermissionUtils
import com.mradmin.yks13.kotlinweather.util.WeatherMathUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ImageView

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    private val PERMISSION_CODE = 1010

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBackgroundAnimation()

        checkPermissions()

        presenter.onViewCreated()

//        try {
//            val f = swipeLayout.javaClass.getDeclaredField("mCircleView")
//            f.isAccessible = true
//            val img = f.get(swipeLayout) as ImageView
//            img.setImageResource(R.drawable.ic_sun_refresh)
//        } catch (e: NoSuchFieldException) {
//            e.printStackTrace()
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//        }

        swipeLayout.setOnRefreshListener {
            presenter.onViewRefresh()
        }
    }

    private fun setBackgroundAnimation() {
        val animDrawable = ivBack.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }

    private fun checkPermissions() {
        if (!PermissionUtils.hasLocationPermission(getContext())) {
            PermissionUtils.requestLocationPermission(this, PERMISSION_CODE)
            return
        }

        enableUserLocation()
    }

    private fun enableUserLocation() {
        if (PermissionUtils.hasLocationPermission(getContext())) {
            presenter.getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                   grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                enableUserLocation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun getWeather(weather: WeatherEntity) {
        print("-------------- weather: $weather")
        tvTitle.text = if (weather.cityName != null && weather.cityName!!.isNotEmpty()) weather.cityName else resources.getString(R.string.app_name)
        tvTemperature.text = resources.getString(R.string.degree_format, WeatherMathUtils.convertFahrenheitToCelsius(weather.currently?.temperature))
        tvWeather.text = weather.currently?.summary
        tvCloud.text = weather.currently?.cloudCover.toString()
        tvHumidity.text = weather.currently?.humidity.toString()
        tvWind.text = weather.currently?.windSpeed.toString()
        tvDate.text = DateUtils.mapExpireDate(weather.currently?.time!!.toLong() * 1000)

    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
        swipeLayout.isRefreshing = true
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }
}
