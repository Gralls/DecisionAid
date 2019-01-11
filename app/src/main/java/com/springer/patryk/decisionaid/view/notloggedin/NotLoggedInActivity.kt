package com.springer.patryk.decisionaid.view.notloggedin

import android.os.Bundle
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.view.base.BaseActivity
import com.springer.patryk.decisionaid.view.notloggedin.login.LoginFragment

class NotLoggedInActivity : BaseActivity() {
	override val mLayoutResId: Int
		get() = R.layout.activity_not_logged_in

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent(LoginFragment())
	}
}