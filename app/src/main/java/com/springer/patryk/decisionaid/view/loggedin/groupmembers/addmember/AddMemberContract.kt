package com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember

import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 31.03.2019.
 */
interface AddMemberContract {

	interface View : BaseFragmentView {
		fun showLoginError(errorId: Int)
		fun onMemberAdded()
	}

	interface Presenter : BasePresenter {
		fun onSubmitClicked(groupId: Int, login: String)
	}
}