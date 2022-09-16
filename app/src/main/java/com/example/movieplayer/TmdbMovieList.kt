package com.example.movieplayer

data class TmdbMovieList (
    val results:List<Movie>   //its a type of an array in the form of list                    // It maps the properties in the results which is an array in json data to the data in the table
                                                 // some changes to be made in Movie Class which is a entity regarding name
)