package kiwari.chats.android.utils

import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun convertTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMM, HH:mm")
        val date = Date(timestamp)
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
}