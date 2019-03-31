package com.springer.patryk.decisionaid.view.loggedin.questions.newquestion

import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.QuestionWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

/**
 * Created by Patryk Springer on 29.03.2019.
 */
class NewQuestionPresenter(private val mView: NewQuestionContract.View) :
	NewQuestionContract.Presenter, BasePresenterImpl(mView) {

	private val questionWS: QuestionWS by instance()
	private val mAnswers: MutableList<Answer> = mutableListOf()

	override fun onAddClicked() {
		mAnswers.add(Answer())
		mView.showAnswers(mAnswers)
	}

	override fun onAnswerRemoved(position: Int) {
		mAnswers.removeAt(position)
	}

	override fun onSendQuestionClicked(userId: Int, groupId: Int?, question: String) {
		val question: Question = Question(mName = question, mAnswers = mAnswers.toList())
		launch {
			val result = safeApiCall {
				questionWS.addQuestion(userId, groupId, QuestionRequest(question)).await()
			}
			if (result is NetResult.Success) {
				mView.dismiss()
			}
		}
	}
}