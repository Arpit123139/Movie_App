package com.example.movieplayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(application: Application):AndroidViewModel(application) {
    private val repo:MovieListRepository= MovieListRepository(application)            // instance of the repository

    val movies:LiveData<List<Movie>> = repo.getMovies()

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus                      // This Live Data is observ in the ListFragment


    fun fetchDataFromNetwork(){
        _loadingStatus.value = LoadingStatus.loading()              // Intially the status is Loading
        viewModelScope.launch {
            //It returns the value according we observe it in the List  Fragment
            _loadingStatus.value =  withContext(Dispatchers.IO){
                //delay(5000)
                repo.fetchFromNetwork()                             // view Model directly does not interact it interact through the repo
            }!!
        }
    }


}














