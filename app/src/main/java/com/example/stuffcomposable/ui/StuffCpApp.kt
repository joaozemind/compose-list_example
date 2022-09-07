package com.example.stuffcomposable.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class StuffCpApp : Application() {

	override fun onCreate() {
		super.onCreate()
		Timber.plant(Timber.DebugTree())
	}
}