package com.supplier.sl_delivery.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Aurora on 2020-04-10.
 */
class DateTimeUtils {
    private var mCalendar = Calendar.getInstance()
    private var mNowTicks : Long = mCalendar.timeInMillis

    fun getFormattedDateTime(dateFormat: String, dateValue: Date): String {
        val mSimpleDateFormat = SimpleDateFormat(dateFormat)
        return try {
            mSimpleDateFormat.format(dateValue).toUpperCase()
        } catch (ex: Exception) {
            "- -"
        }
    }

    fun getDate(milliSeconds: Long, dateFormat : String): String? {
        val formatter = SimpleDateFormat(dateFormat)
        mCalendar.timeInMillis = milliSeconds
        return formatter.format(mCalendar.time)
    }

    fun getDayOfTheWeek(milliSeconds: Long): String? {
        mCalendar.timeInMillis = milliSeconds
        when(mCalendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> {
                return "Sunday"
            }
            2 -> {
                return "Monday"
            }
            3 -> {
                return "Tuesday"
            }
            4 -> {
                return "Wednesday"
            }
            5 -> {
                return "Thursday"
            }
            6 -> {
                return "Friday"
            }
            0-> {
                return "Saturday"
            }
        }
        return  "- -"
    }

    fun getDifferenceRange(milliSeconds: Long): String? {
        mCalendar.timeInMillis = milliSeconds
        var today : Int = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return when {
            mCalendar.get(Calendar.DAY_OF_YEAR) == today -> {
                "Today"
            }
            mCalendar.get(Calendar.DAY_OF_MONTH) - today == 1 -> {
                "Tomorrow"
            }
            mCalendar.get(Calendar.WEEK_OF_YEAR) - Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) == 1 -> {
                "Next Week"
            }
            mCalendar.get(Calendar.WEEK_OF_YEAR) - Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) == 0 -> {
                "This Week"
            }
            else -> "Upcoming"
        }
    }
}
