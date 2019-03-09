package com.springer.patryk.decisionaid

import android.app.Application
import com.springer.patryk.decisionaid.model.network.networkModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

class DecisionAidApp : Application(), KodeinAware {
	override val kodein: Kodein
		get() = Kodein.lazy {
			import(networkModule)
		}

	override fun onCreate() {
		super.onCreate()
		Timber.plant(Timber.DebugTree())
	}
}