package com.bakikocak.android_mvvm_sample.repository

import com.bakikocak.android_mvvm_sample.data.api.TrendingMovieService
import com.bakikocak.android_mvvm_sample.data.model.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class TrendingMoviesRepository @Inject constructor(private val trendingMovieService: TrendingMovieService) {

    suspend fun getTrendingMovies(): Response<MovieResponse> =
        trendingMovieService.getTrendingMovies()
}