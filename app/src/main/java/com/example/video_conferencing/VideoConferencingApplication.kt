package com.example.video_conferencing

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.UUID


@HiltAndroidApp
class VideoConferencingApplication : Application(){


    override fun onCreate() {
        super.onCreate()
    }



    companion object{
        val username = UUID.randomUUID().toString().substring(0,6)
    }


}