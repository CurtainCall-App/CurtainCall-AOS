package com.cmc.curtaincall

import android.app.Application
import timber.log.Timber

class CurtainCallApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}