package com.aliumujib.cryptoapp.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.plant
import timber.log.Timber.DebugTree


@HiltAndroidApp
class ApplicationClass : Application(){


    override fun onCreate() {
        super.onCreate()

        plant(DebugTree())

    }

}
