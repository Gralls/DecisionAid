package com.springer.patryk.decisionaid.view.loggedin.questions.newquestion

import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.view.base.BaseFragmentView
import com.springer.patryk.korkidajmi.view.base.BasePresenter

/**
 * Created by Patryk Springer on 29.03.2019.
 */
interface NewQuestionContract {

	interface View : BaseFragmentView {
		fun showAnswers(list: List<Answer>)
		fun dismiss()
	}

	interface Presenter : BasePresenter {
		fun onAddClicked()
		fun onAnswerRemoved(position: Int)
		fun onSendQuestionClicked(userId: Int, groupId: Int?, question: String)
	}
}