package com.example.movieplayer

import android.content.Context
import androidx.room.*

@TypeConverters(DbTypeConverters::class)                      // Use to convert Date which is not the format know by the sqlLite so to convert it into Long we use this Converters
@Database(entities = [Movie::class], version = 1)             // Inside the database we are going to store the date as Long
abstract class MovieDatabase :RoomDatabase() {

    abstract fun movieListDao():MovieListDao
    abstract fun movieDetailDao():MovieDetailDao

    companion object{
        @Volatile
        private var instance:MovieDatabase?=null

        fun getDatabase(context: Context) = instance
            ?: synchronized(this) {
                     Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build().also { instance = it }
            }
    }


}