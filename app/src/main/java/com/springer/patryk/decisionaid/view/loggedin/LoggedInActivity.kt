package com.springer.patryk.decisionaid.view.loggedin

import android.os.Bundle
import androidx.annotation.DrawableRes
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.extensions.gone
import com.springer.patryk.decisionaid.extensions.visible
import com.springer.patryk.decisionaid.view.base.BaseActivity
import com.springer.patryk.decisionaid.view.loggedin.groups.GroupsFragment
import kotlinx.android.synthetic.main.activity_logged_in.*

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class LoggedInActivity : BaseActivity() {

	override val mLayoutResId: Int
		get() = R.layout.activity_logged_in

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent(GroupsFragment())
		setSupportActionBar(bap_bottom_menu)
		supportActionBar?.setDisplayHomeAsUpEnabled(false)
		supportActionBar?.setDisplayShowHomeEnabled(false)
	}

	fun setBottomFabAction(
		@DrawableRes
		fabIcon: Int, action: () -> Unit
	) {
		fab_bottom_menu_action.setImageDrawable(getDrawable(fabIcon))
		fab_bottom_menu_action.setOnClickListener { action() }
	}

	fun hideBottomBar() {
		bap_bottom_menu.gone()
		fab_bottom_menu_action.gone()
	}

	fun showBottombar() {
		bap_bottom_menu.visible()
		fab_bottom_menu_action.visible()
	}
}