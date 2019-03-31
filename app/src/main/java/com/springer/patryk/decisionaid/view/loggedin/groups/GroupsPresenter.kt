package com.springer.patryk.decisionaid.view.loggedin.groups

import com.springer.patryk.decisionaid.model.UsersGroup
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.GroupsWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.loggedin.groups.model.NewGroup
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class GroupsPresenter(private val mView: GroupsContract.View) : GroupsContract.Presenter,
		BasePresenterImpl(mView) {

	private val mGroupsWS: GroupsWS by instance()

	override fun refreshGroups(userId: Int) {
		launch {
			val result = safeApiCall { mGroupsWS.getUserGroups(userId).await() }
			if (result is NetResult.Success) {
				val response = result.data
				handleResponse(response)
			}
		}
	}

	private fun handleResponse(response: Response<List<UsersGroup>>) {
		when (response.code()) {
			HttpURLConnection.HTTP_OK -> {
				val groups: List<UsersGroup> = response.body() ?: emptyList()
				if (groups.isEmpty()) {
					mView.showEmptyList()
				} else {
					mView.showGroupList(groups)
				}
			}

		}
	}

	override fun onAddNewGroupClicked() {
		mView.openNewGroupDialog()
	}

	override fun onNewGroupSubmit(groupName: String, adminId: Int) {
		launch {
			val result =
				safeApiCall { mGroupsWS.createNewGroup(NewGroup(groupName, adminId)).await() }
			if (result is NetResult.Success) {
				handleNewGroupResponse(result.data)
			}
		}
	}

	private fun handleNewGroupResponse(response: Response<UsersGroup>) {
		when (response.code()) {
			HttpURLConnection.HTTP_CREATED -> refreshGroups(response.body()?.mAdmin?.mId ?: -1)
		}
	}

	override fun onGroupClicked(groupId: Int) {
		mView.openGroupsQuestion(groupId)
	}
}