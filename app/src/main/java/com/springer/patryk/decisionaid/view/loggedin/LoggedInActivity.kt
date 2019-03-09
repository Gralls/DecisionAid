package com.springer.patryk.decisionaid.view.loggedin

import android.os.Bundle
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.view.base.BaseActivity
import com.springer.patryk.decisionaid.view.loggedin.groups.GroupsFragment

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class LoggedInActivity : BaseActivity() {

	override val mLayoutResId: Int
		get() = R.layout.activity_logged_in

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent(GroupsFragment())
	}
}