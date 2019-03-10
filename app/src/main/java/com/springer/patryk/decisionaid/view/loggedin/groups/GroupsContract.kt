package com.springer.patryk.decisionaid.view.loggedin.groups

import com.springer.patryk.decisionaid.model.UsersGroup
import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 09.03.2019.
 */
interface GroupsContract {

	interface View : BaseFragmentView {
		fun showGroupList(groups: List<UsersGroup>)
		fun showEmptyList()
		fun openNewGroupDialog()
	}

	interface Presenter : BasePresenter {
		fun refreshGroups(userId: Int)
		fun onAddNewGroupClicked()
		fun onNewGroupSubmit(groupName: String, adminId: Int)
	}
}