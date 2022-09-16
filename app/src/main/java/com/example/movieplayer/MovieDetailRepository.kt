package com.example.movieplayer

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData

class MovieDetailRepository(context: Application) {
    private val movieDetailDao:MovieDetailDao=MovieDatabase.getDatabase(context).movieDetailDao()

    fun getMovie(id:Long): LiveData<Movie>{
        return movieDetailDao.getMovie(id)
    }
}