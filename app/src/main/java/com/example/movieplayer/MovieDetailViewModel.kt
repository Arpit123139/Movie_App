package com.example.movieplayer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel(id: Long, application: Application): ViewModel(){
    private val repo: MovieDetailRepository = MovieDetailRepository(application)

    val movie: LiveData<Movie> =
        repo.getMovie(id)
}