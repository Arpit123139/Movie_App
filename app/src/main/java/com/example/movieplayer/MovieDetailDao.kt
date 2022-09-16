package com.example.movieplayer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM movie WHERE `id`= :id ")       // The :id is the same name in the getMovie parameter
    fun getMovie(id:Long):LiveData<Movie>

    // the main work of the detail fragment to get the id and the get thr movie
}