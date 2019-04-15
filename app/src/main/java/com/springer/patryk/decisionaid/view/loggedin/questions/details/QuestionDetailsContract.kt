package com.springer.patryk.decisionaid.view.loggedin.questions.details

import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 04.04.2019.
 */
interface QuestionDetailsContract {

	interface View : BaseFragmentView {
		var questionName: String
		fun showAnswers(answers: List<Answer>)
		fun notifyItemMoved(fromPosition: Int, toPosition: Int)
	}

	interface Presenter : BasePresenter {
		fun refreshQuestionDetails(questionId: Int)
		fun onSubmitButtonClicked(userId: Int)
	}
}