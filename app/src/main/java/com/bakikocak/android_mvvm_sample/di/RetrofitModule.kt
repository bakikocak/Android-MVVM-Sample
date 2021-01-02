package com.bakikocak.android_mvvm_sample.di

import com.bakikocak.android_mvvm_sample.BuildConfig
import com.bakikocak.android_mvvm_sample.data.api.service.TrendingMovieService
import com.bakikocak.android_mvvm_sample.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideTrendingService(retrofit: Retrofit): TrendingMovieService = retrofit.create(TrendingMovieService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    fun providesOkHttp(@Named("apiKeyInterceptor") apiKeyInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Named("apiKeyInterceptor")
    fun providesApiKeyInterceptor(): Interceptor = Interceptor {
        val original = it.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        it.proceed(request)
    }

}