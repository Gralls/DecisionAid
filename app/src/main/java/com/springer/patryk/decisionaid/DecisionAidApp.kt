package com.springer.patryk.decisionaid

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class DecisionAidApp : Application(), KodeinAware {
	override val kodein: Kodein
		get() = Kodein.lazy {

		}
}