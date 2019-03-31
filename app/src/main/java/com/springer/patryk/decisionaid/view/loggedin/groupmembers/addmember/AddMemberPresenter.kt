package com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.GroupsWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember.model.NewMemberRequest
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import java.net.HttpURLConnection

/**
 * Created by Patryk Springer on 31.03.2019.
 */
class AddMemberPresenter(private val mView: AddMemberContract.View) : AddMemberContract.Presenter,
	BasePresenterImpl(mView) {

	private val mGroupsWS: GroupsWS by instance()

	override fun onSubmitClicked(groupId: Int, login: String) {
		launch {
			val result = safeApiCall {
				mGroupsWS.assignUserToGroup(groupId, NewMemberRequest(login)).await()
			}
			if (result is NetResult.Success) {
				when (result.data.code()) {
					HttpURLConnection.HTTP_CREATED -> {
						mView.onMemberAdded()
					}
					HttpURLConnection.HTTP_CONFLICT -> {
						mView.showLoginError(R.string.msg_user_in_group)
					}
					HttpURLConnection.HTTP_NOT_FOUND -> {
						mView.showLoginError(R.string.msg_user_not_found)
					}
				}
			}
		}
	}
}