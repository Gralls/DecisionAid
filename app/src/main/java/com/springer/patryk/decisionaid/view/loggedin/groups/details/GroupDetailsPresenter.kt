package com.springer.patryk.decisionaid.view.loggedin.groups.details

import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.GroupsWS
import com.springer.patryk.decisionaid.model.network.endpoints.QuestionWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.loggedin.groups.details.model.GroupDetails
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

/**
 * Created by Patryk Springer on 10.03.2019.
 */
class GroupDetailsPresenter(private val mView: GroupDetailsContract.View) :
	GroupDetailsContract.Presenter, BasePresenterImpl(mView) {

	private val mQuestionWS: QuestionWS by instance()
	private val mGroupWS: GroupsWS by instance()
	private var mQuestions: List<Question> = emptyList()
	private lateinit var mGroupDetails: GroupDetails
	override fun refreshQuestionList(userId: Int, groupId: Int) {
		launch {
			val result = safeApiCall { mQuestionWS.getQuestionList(userId, groupId).await() }
			if (result is NetResult.Success) {
				mQuestions = result.data.body()?.sortedBy { it.mIsClosed } ?: emptyList()
				mView.refreshQuestionList(mQuestions)
			}
		}
	}

	override fun refreshGroupDetails(groupId: Int) {
		launch {
			val result = safeApiCall { mGroupWS.getGroupDetails(groupId).await() }
			if (result is NetResult.Success) {
				mGroupDetails = result.data.body() ?: return@launch
				mView.setGroupName(mGroupDetails.name)
				val name = mGroupDetails.admin?.mName ?: ""
				val surname = mGroupDetails.admin?.mSurname ?: ""
				mView.setGroupAdmin(name, surname)
			}
		}
	}
}