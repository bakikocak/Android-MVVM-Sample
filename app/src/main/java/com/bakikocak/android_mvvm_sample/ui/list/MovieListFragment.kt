package com.bakikocak.android_mvvm_sample.ui.list

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakikocak.android_mvvm_sample.BuildConfig
import com.bakikocak.android_mvvm_sample.R
import com.bakikocak.android_mvvm_sample.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var movieAdapter: MovieListAdapter

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupMovieRv()

        // Navigate to detail fragment passing its id.
        movieAdapter.setOnItemClickListener {
            val bundle = bundleOf("movieId" to it.id)
            navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        viewModel.getTrendingMovies().observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        movieAdapter.differ.submitList(movieResponse.results)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        //show error message
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.loadTrendingMovies()
    }

    private fun showProgressBar() {
        list_progress_bar.visibility = VISIBLE
    }

    private fun hideProgressBar() {
        list_progress_bar.visibility = GONE
    }

    private fun setupMovieRv() {
        movieAdapter = MovieListAdapter()
        movie_list_rv.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(activity, getSpanCount())
        }
    }

    private fun getSpanCount(): Int =
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
}