package com.codebook.petfacts

import android.app.Application
import android.util.Log
import com.codebook.petfacts.Utils.isInternetConnected
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PatFactsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("PatFactsApplication", "onCreate: ${isInternetConnected(this)}")
    }
}