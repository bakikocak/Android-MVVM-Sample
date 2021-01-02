package com.bakikocak.android_mvvm_sample.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakikocak.android_mvvm_sample.data.model.MovieResponse
import com.bakikocak.android_mvvm_sample.repository.TrendingMoviesRepository
import com.bakikocak.android_mvvm_sample.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieListViewModel @ViewModelInject constructor(private val repository: TrendingMoviesRepository) :
    ViewModel() {

    private val trendingMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    fun getTrendingMovies() = trendingMovies

    fun loadTrendingMovies() = viewModelScope.launch {
        trendingMovies.postValue(Resource.Loading())
        val trendingMoviesResponse = repository.getTrendingMovies()
        trendingMovies.postValue(handleResponse(trendingMoviesResponse))
    }

    private fun handleResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}