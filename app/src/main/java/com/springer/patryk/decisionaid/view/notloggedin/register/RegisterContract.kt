package com.springer.patryk.decisionaid.view.notloggedin.register

import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 09.03.2019.
 */
interface RegisterContract {

	interface View : BaseFragmentView {
		fun showLoginError(errorId: Int?)
		fun showNameError(errorId: Int?)
		fun showSurnameError(errorId: Int?)
		fun showPasswordError(errorId: Int?)
		fun showRepeatPasswordError(errorId: Int?)
		fun onRegisterCompleted(userId: Int)
	}

	interface Presenter : BasePresenter {
		fun onRegisterClicked(login: String, name: String, surname: String, password: String,
							  repeatPassword: String)
	}
}