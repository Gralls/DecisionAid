package com.springer.patryk.decisionaid.view.notloggedin.login

import android.os.Bundle
import android.view.View
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.view.base.BaseFragment
import org.kodein.di.generic.instance

/**
 * Created by Patryk Springer on 2019-01-11.
 */
class LoginFragment : BaseFragment(), LoginContract.View {

	override val layoutResId: Int
		get() = R.layout.fragment_login

	private val mPresenter: LoginContract.Presenter by instance()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}
}