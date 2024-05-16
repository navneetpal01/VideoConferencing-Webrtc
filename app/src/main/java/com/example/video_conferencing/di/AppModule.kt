package com.example.video_conferencing.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }

    //Means the same  -> no need of singleton for the application context as the context remains
    // same entire application they are already single instance

//    @Provides
//    @Singleton
//    fun providesContext(application: Application): Application {
//        return application
//    }


    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

}