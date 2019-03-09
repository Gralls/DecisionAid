package com.springer.patryk.decisionaid.view.notloggedin.login

import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 2019-01-11.
 */
interface LoginContract {

	interface View : BaseFragmentView {
		fun onLoginSuccess(userId: Int)
		fun setLoginError(errorId: Int?)
		fun setPasswordError(errorId: Int?)
	}

	interface Presenter : BasePresenter {
		fun onLoginClicked(login: String, password: String)
	}
}