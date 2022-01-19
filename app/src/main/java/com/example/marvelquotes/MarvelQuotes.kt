package com.example.marvelquotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelQuotes: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
