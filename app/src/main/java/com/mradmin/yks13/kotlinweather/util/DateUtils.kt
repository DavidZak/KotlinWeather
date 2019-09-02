package com.mradmin.yks13.kotlinweather.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    //DATEFORMAT
    val DATEFORMAT_HUNTERS_MAP = "d MMM '‘' yy, HH:mm"
    val DATEFORMAT_YYYY_MM_DDT_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss"
    val DATEFORMAT_YYYY_MM_DDT_HH_MM = "yyyy-MM-dd'T'HH:mm"
    val DATEFORMAT_YYYY_MM_DDT_HH_MM_SS_SSSSSSS = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"
    val DATEFORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    val DATEFORMAT_DD_MM_YYYY = "dd.MM.yyyy"
    val DATEFORMAT_DD_MMMM_YYYY = "dd MMMM yyyy"
    val DATEFORMAT_HH_MM = "HH:mm"
    val DATEFORMAT_HH_MM_SS = "HH:mm:ss"
    val DATEFORMAT_EEE_DD_LLL_YYYY = "EEE dd LLL yyyy"
    val DATEFORMAT_DD_LLL_YYYY = "dd LLL yyyy"
    val DATEFORMAT_DD_LLL = "dd LLL"
    val DATEFORMAT_EEEE = "EEEE"
    val DATEFORMAT_DD_MM = "dd.MM"
    val DATEFORMAT_MMMMM = "MMMM"


    fun dateToString(date: Date, formatString: String): String {
        var formattedDate = ""
        val format = SimpleDateFormat(formatString, Locale("ru"))
        try {
            formattedDate = format.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formattedDate
    }

    fun dateToString(date: Long?, formatString: String): String {
        var formattedDate = ""
        val format = SimpleDateFormat(formatString, Locale("ru"))
        try {
            formattedDate = format.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formattedDate
    }

    fun stringToDate(date: String, formatString: String): Date? {
        var formattedDate: Date? = null
        if (date.isEmpty()) {
            return null
        }
        val format = SimpleDateFormat(formatString, Locale("ru"))
        try {
            formattedDate = format.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formattedDate
    }

    fun mapExpireDate(date: Long): String {
        var formattedDate = ""
        val format = SimpleDateFormat(DATEFORMAT_HUNTERS_MAP, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date

        try {
            formattedDate = format.format(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formattedDate
    }
}
