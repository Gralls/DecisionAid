package com.springer.patryk.decisionaid.view.notloggedin

import android.content.Intent
import android.os.Bundle
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseActivity
import com.springer.patryk.decisionaid.view.loggedin.LoggedInActivity

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class SplashActivity : BaseActivity() {

	override val mLayoutResId: Int
		get() = R.layout.activity_splash

	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(R.style.AppTheme)
		super.onCreate(savedInstanceState)
		if (PreferenceHelper.getCurrentUserId(this) == PreferenceHelper.NOT_SET_INT) {
			startActivity(Intent(this, NotLoggedInActivity::class.java))
		} else {
			startActivity(Intent(this, LoggedInActivity::class.java))
		}
		finish()
	}
}