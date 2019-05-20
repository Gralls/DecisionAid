package com.springer.patryk.decisionaid.view.loggedin.questions.details

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.QuestionWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.loggedin.questions.details.model.AnswerRequest
import com.springer.patryk.decisionaid.view.loggedin.questions.details.model.Preferences
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import java.net.HttpURLConnection
import java.util.*

/**
 * Created by Patryk Springer on 04.04.2019.
 */
class QuestionDetailsPresenter(private val mView: QuestionDetailsContract.View) :
	QuestionDetailsContract.Presenter, BasePresenterImpl(mView) {

	val questionWS: QuestionWS by instance()
	var mAnswers: List<Answer> = emptyList()
	var mPreferenceMatrix: MutableList<Preferences> = mutableListOf()
	var mQuestionId: Int = -1
	override fun refreshQuestionDetails(questionId: Int) {
		mQuestionId = questionId
		launch {
			val result = safeApiCall { questionWS.getQuestion(mQuestionId).await() }
			if (result is NetResult.Success) {
				val question = result.data.body() ?: Question()
				mAnswers = question.mAnswers
				generatePreferenceMatrix()
				mView.questionName = question.mName
				mView.showAnswers(mPreferenceMatrix)
			}
		}
	}

	private fun generatePreferenceMatrix() {
		mAnswers.forEachIndexed { firstIndex, firstAnswer ->
			mAnswers.forEachIndexed { secondIndex, secondAnswer ->
				if (firstIndex != secondIndex) {
					mPreferenceMatrix.add(Preferences(firstAnswer, secondAnswer))
				}
			}
		}
	}

	override fun onSubmitButtonClicked(userId: Int) {
		launch {
			mAnswers.forEachIndexed { index, answer ->
				answer.mPosition = index
			}
			val result = safeApiCall {
				questionWS.answerQuestion(
					userId, mQuestionId, AnswerRequest(mPreferenceMatrix)
				).await()
			}
			if (result is NetResult.Success) {
				when (result.data.code()) {
					HttpURLConnection.HTTP_OK -> {
						mView.showFragmentSnackbar(R.string.msg_answer_saved)
					}
					HttpURLConnection.HTTP_CONFLICT -> {
						mView.showFragmentSnackbar(R.string.msg_question_already_answered)
					}
				}
			}
		}
	}
}