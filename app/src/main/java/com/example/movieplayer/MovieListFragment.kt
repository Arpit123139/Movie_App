package com.example.movieplayer


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(MovieListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(movie_list){
            adapter = MovieAdapter {
                val bundle=Bundle()
                bundle.putLong("ID",it)

                findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment,bundle)
                    //MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(bundle)

                // for this we add a dependency in project Gradle id 'androidx.navigation.safeargs' version '2.4.2' apply false
                //and a plugin in module gradle
                //id 'androidx.navigation.safeargs'

            }
        }

        // changes in the Live Data that is the List of movie is observedd here
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            (movie_list.adapter as MovieAdapter).submitList(it)
            if(it.isEmpty())
                viewModel.fetchDataFromNetwork()
        })

        viewModel.loadingStatus.observe(viewLifecycleOwner, Observer {loadingStatus ->
            when {
                loadingStatus?.status == StatusEnum.LOADING -> {
                    loading_status.visibility = View.VISIBLE
                    status_error.visibility = View.INVISIBLE
                }
                loadingStatus?.status == StatusEnum.SUCCESS -> {
                    loading_status.visibility = View.INVISIBLE
                    status_error.visibility = View.INVISIBLE
                }
                loadingStatus?.status == StatusEnum.ERROR -> {
                    loading_status.visibility = View.INVISIBLE
                    showErrorMessage(loadingStatus.errorCode, loadingStatus.message)
                    status_error.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showErrorMessage(errorCode: ErrorCodeEnum?, message: String?) {
        when (errorCode) {               // when is ude to check which type of error code
            ErrorCodeEnum.NO_DATA -> status_error.text = getString(R.string.error_no_data)
            ErrorCodeEnum.NETWORK_ERROR -> status_error.text = getString(R.string.error_network)
            ErrorCodeEnum.UNKNOWN_ERROR -> status_error.text = getString(R.string.error_unknown, message)
        }
    }





 }
