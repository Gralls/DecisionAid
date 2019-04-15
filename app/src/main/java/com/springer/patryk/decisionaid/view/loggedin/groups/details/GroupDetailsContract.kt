package com.springer.patryk.decisionaid.view.loggedin.groups.details

import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 10.03.2019.
 */
interface GroupDetailsContract {

	interface View : BaseFragmentView {
		fun refreshQuestionList(questions: List<Question>)
		fun setGroupName(name: String)
		fun setGroupAdmin(adminName: String, adminSurname: String)
		fun openMembersList(membersList: List<User>)
		fun openQuestionDetails(questionId: Int)
	}

	interface Presenter : BasePresenter {
		fun refreshQuestionList(userId: Int, groupId: Int)
		fun refreshGroupDetails(groupId: Int)
		fun onMembersListClicked()
		fun onQuestionClicked(questionId: Int)
	}
}