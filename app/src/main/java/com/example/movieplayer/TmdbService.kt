package com.example.movieplayer

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TmdbService {
    //JSON DATA  URL GIVEN BELOW
    //https://api.themoviedb.org/3/discover/movie?api_key=b2b679106406f533b2281b5f887cb83e&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"           // in the json we get only the name of the images this is a standard format for creating a URL we also append the name //https://image.tmdb.org/t/p/original/
        const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w300"
       // Append this to the backdrop/poster path. for example: 'backdrop_path': '/eSVvx8xys2NuFhl8fevXt41wX7v.jpg // Instead of orignal we can specify the width as w300


        private val retrofitService by lazy {                           //lazy keyword means that execute this function only when it is ask
            // Add api key to every request
            val interceptor = Interceptor { chain ->
                val request = chain.request()
                val url = request.url().newBuilder()
                    .addQueryParameter("api_key", "b2b679106406f533b2281b5f887cb83e")           // The interceptor when the request Arrives it attach The TMDB api key declared in the mainActivity
                    .build()
                val newRequest = request.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)

            }

            val httpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()              // it request to access the http server

            val gson = GsonBuilder()                                         // gson is the inbuilt library that convert the json format data into the data object
                .setDateFormat("yyyy-MM-dd HH:mm:ss")                        // setting a date format
                .create()

            Retrofit.Builder()                      // creating a instance
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TmdbService::class.java)
        }

        fun getInstance(): TmdbService {
            return retrofitService
        }
    }

//    @GET("discover/movie?certification_country=US&adult=false&vote_count.gte=100&" +
//            "with_original_language=en&sort_by=primary_release_date.desc")              // parameter that are required by API that we can see in the TryOut section in TMDB API https://developers.themoviedb.org/3/discover/movie-discover
   @GET
    suspend fun getMovies(): Response<TmdbMovieList>          // Response message from The HTTP of the type TmdbMovieList
}