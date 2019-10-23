package com.my.githubviewer.data.transformer

import androidx.room.TypeConverter
import java.util.*

class DateConverters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

}