package com.springer.patryk.decisionaid.view.loggedin.groupmembers

import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 31.03.2019.
 */
interface GroupMembersContract {

	interface View : BaseFragmentView {
		fun showMembersList(membersList: List<User>)
		fun showNewMemberDialog(groupId: Int)
	}

	interface Presenter : BasePresenter {
		fun refreshMembers(groupId: Int)
		fun onMemberRemoved(position: Int)
		fun onAddNewMemberClicked()
	}
}