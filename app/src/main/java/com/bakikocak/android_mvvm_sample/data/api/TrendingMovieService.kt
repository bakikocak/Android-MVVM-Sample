package com.bakikocak.android_mvvm_sample.data.api

import com.bakikocak.android_mvvm_sample.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface TrendingMovieService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies() : Response<MovieResponse>
}