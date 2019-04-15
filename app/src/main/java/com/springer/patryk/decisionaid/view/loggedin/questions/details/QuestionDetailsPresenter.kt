package com.springer.patryk.decisionaid.view.loggedin.questions.details

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.QuestionWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.loggedin.questions.details.model.AnswerRequest
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import java.net.HttpURLConnection
import java.util.*

/**
 * Created by Patryk Springer on 04.04.2019.
 */
class QuestionDetailsPresenter(private val mView: QuestionDetailsContract.View) :
	QuestionDetailsContract.Presenter, BasePresenterImpl(mView), OnItemTouchListener {

	val questionWS: QuestionWS by instance()
	var mAnswers: List<Answer> = emptyList()
	var mQuestionId: Int = -1
	override fun refreshQuestionDetails(questionId: Int) {
		mQuestionId = questionId
		launch {
			val result = safeApiCall { questionWS.getQuestion(mQuestionId).await() }
			if (result is NetResult.Success) {
				val question = result.data.body() ?: Question()
				mAnswers = question.mAnswers
				mView.questionName = question.mName
				mView.showAnswers(mAnswers)
			}
		}
	}

	override fun onItemMoved(fromPosition: Int, toPosition: Int) {
		Collections.swap(mAnswers, fromPosition, toPosition)
		mView.notifyItemMoved(fromPosition, toPosition)
	}

	override fun onSubmitButtonClicked(userId: Int) {
		launch {
			mAnswers.forEachIndexed { index, answer ->
				answer.mPosition = index
			}
			val result = safeApiCall {
				questionWS.answerQuestion(
					userId, mQuestionId, AnswerRequest(mAnswers)
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