package com.example.movieplayer

import androidx.room.TypeConverter
import java.util.*

class DbTypeConverters {

    @TypeConverter
    fun fromTimestamp(value: Long): Date?{                     // function name does not matter its just the parameter it sees if it is a Long then first function else second
        return if(value==null) null else Date(value)

    }
    @TypeConverter
    fun dateToTimestamp(date:Date): Long?{

        return date?.time
    }


}
