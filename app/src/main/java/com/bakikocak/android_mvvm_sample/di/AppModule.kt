package com.bakikocak.android_mvvm_sample.di

import android.content.Context
import com.bakikocak.android_mvvm_sample.MovieApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MovieApp {
        return app as MovieApp
    }
}