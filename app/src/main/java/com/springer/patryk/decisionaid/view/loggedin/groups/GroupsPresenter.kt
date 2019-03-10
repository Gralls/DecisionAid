package com.springer.patryk.decisionaid.view.loggedin.groups

import com.springer.patryk.decisionaid.model.UsersGroup
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.GroupsWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
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
}