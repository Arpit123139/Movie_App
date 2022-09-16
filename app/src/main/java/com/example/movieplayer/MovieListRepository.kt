package com.example.movieplayer

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception
import java.net.UnknownHostException

class MovieListRepository(context:Application) {
    private val movieListDao:MovieListDao=MovieDatabase.getDatabase(context).movieListDao()
    private val tmdbService by lazy { TmdbService.getInstance() }

    fun getMovies():LiveData<List<Movie>>{
        return movieListDao.getMovies()
    }

    suspend fun fetchFromNetwork()=
        try {
            val result=tmdbService.getMovies()
            if(result.isSuccessful){
                val movieList=result.body()                // It goes to the    Tmdb Movie List to map the functionality in the result property of the json Data to the entities decleared in the Movie Class
                movieList?.let { movieListDao.insertMovies(it.results) }  //passing directly the results parameter in json data which consist the parameter such as title poster_path and other  // it ==TmdbMovieList
                LoadingStatus.success()
            }else{
                LoadingStatus.error(ErrorCodeEnum.NO_DATA)         // The request has been made but it does not return a data
            }
        }catch (ex :UnknownHostException){
            LoadingStatus.error(ErrorCodeEnum.NETWORK_ERROR)

        }catch (ex:Exception){
            LoadingStatus.error(ErrorCodeEnum.UNKNOWN_ERROR,ex.message)        // The message is generated automatically by the Exception
        }



}