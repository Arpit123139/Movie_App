package com.example.movieplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class MovieDetailFragment : Fragment() {
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val id=arguments?.getLong("ID")
        val viewModelFactory = id?.let { MovieDetailViewModelFactory(it, requireActivity().application) } // it ==id

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
    }


    private fun setData(movie: Movie){


        Glide.with(requireActivity())
            .load(TmdbService.POSTER_BASE_URL + movie.posterPath)
            .error(R.drawable.ic_launcher_background)
            .into(movie_poster)             // layout

        Glide.with(requireActivity())
            .load(TmdbService.BACKDROP_BASE_URL + movie.backdropPath)
            .error(R.drawable.ic_launcher_background)
            .into(movie_backdrop)

        movie_title.text = movie.title
        movie_overview.text = movie.overview

        movie_release_date.text = movie.releaseDate.readableFormat()
    }
}
