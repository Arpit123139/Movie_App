package com.example.movieplayer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieListDao {
    // THE DETAIL OF THE QUERY IS GIVEN IN Room Database Example-Architecture
    @Query("SELECT * FROM movie ORDER BY release_date DESC")
    fun getMovies():LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies:List<Movie>)
}