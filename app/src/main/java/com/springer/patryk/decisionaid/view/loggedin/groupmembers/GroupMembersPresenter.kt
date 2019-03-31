package com.springer.patryk.decisionaid.view.loggedin.groupmembers

import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.GroupsWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

/**
 * Created by Patryk Springer on 31.03.2019.
 */
class GroupMembersPresenter(private val mView: GroupMembersContract.View) :
	GroupMembersContract.Presenter, BasePresenterImpl(mView) {

	private var mMembersList: MutableList<User> = mutableListOf()
	private var mGroupId: Int = -1
	private val mGroupWS: GroupsWS by instance()
	override fun refreshMembers(groupId: Int) {
		launch {
			val result = safeApiCall { mGroupWS.getGroupDetails(groupId).await() }
			if (result is NetResult.Success) {
				mMembersList = result.data.body()?.members?.toMutableList() ?: mutableListOf()
				mView.showMembersList(mMembersList)
			}
		}
		mGroupId = groupId
	}

	override fun onMemberRemoved(position: Int) {
		val user: User = mMembersList[position]
		mMembersList.removeAt(position)
		launch {
			val result = safeApiCall {
				mGroupWS.removeMemberFromGroup(mGroupId, user.mId).await()
			}
		}
	}

	override fun onAddNewMemberClicked() {
		mView.showNewMemberDialog(mGroupId)
	}
}