package com.bakikocak.android_mvvm_sample.ui.list

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
import com.bakikocak.android_mvvm_sample.BuildConfig
import com.bakikocak.android_mvvm_sample.R
import com.bakikocak.android_mvvm_sample.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    lateinit var navController: NavController

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

        // Navigate to detail fragment
        go_to_detail_btn.setOnClickListener {
            /*val bundle = bundleOf("movieId" to 1) // dummy id is passed for now.
            navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)*/
            viewModel.loadTrendingMovies()
        }

        viewModel.getTrendingMovies().observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieResponse ->
                        // show data
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
}