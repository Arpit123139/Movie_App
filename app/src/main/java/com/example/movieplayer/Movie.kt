package com.example.movieplayer

import android.icu.text.CaseMap
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movie")                  // THESE ARE THE DATA EXTRACTED FROM THE API
data class Movie (
    @PrimaryKey
    val id:Long,

    val title:String,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath:String,
    @ColumnInfo(name="backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath:String,

    val overview:String,

    @ColumnInfo(name="release_date")               // the release date is recognized by this name inside the DataBase
    @SerializedName("release_date")           // these are the name in json data to be mapped with the entity  json data has the name rrelease_date which is maapped to release date
    val releaseDate: Date

)