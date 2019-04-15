package com.springer.patryk.decisionaid.view.loggedin.questions.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcinmoskala.kotlinandroidviewbindings.bindToText
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.LoggedInActivity
import com.springer.patryk.decisionaid.view.loggedin.questions.details.adapters.AnswersListAdapter
import kotlinx.android.synthetic.main.fragment_question_details.*

/**
 * Created by Patryk Springer on 04.04.2019.
 */
class QuestionDetailsFragment : BaseFragment(), QuestionDetailsContract.View {

	override val layoutResId: Int = R.layout.fragment_question_details

	private val mPresenter: QuestionDetailsContract.Presenter by lazy {
		QuestionDetailsPresenter(
			this
		)
	}
	private val mAdapter: AnswersListAdapter by lazy { AnswersListAdapter() }
	override var questionName: String by bindToText(R.id.tv_question_details)

	companion object {
		const val QUESTION_ID_KEY = "questionId"
		fun newInstance(questionId: Int): QuestionDetailsFragment {
			val fragment = QuestionDetailsFragment()
			val bundle = Bundle()
			bundle.putInt(QUESTION_ID_KEY, questionId)
			fragment.arguments = bundle
			return fragment
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(mBaseActivity as? LoggedInActivity)?.hideBottomBar()
	}

	override fun onDestroy() {
		super.onDestroy()
		(mBaseActivity as? LoggedInActivity)?.showBottombar()
	}

	override fun onResume() {
		super.onResume()
		arguments?.let { bundle ->
			val questionId = bundle.getInt(QUESTION_ID_KEY)
			mPresenter.refreshQuestionDetails(questionId)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val touchHelperCallback = AnswerTouchHelperCallback(mPresenter as OnItemTouchListener)

		val touchHelper = ItemTouchHelper(touchHelperCallback)
		touchHelper.attachToRecyclerView(rv_question_details_answers)
		rv_question_details_answers.adapter = mAdapter
		rv_question_details_answers.layoutManager = LinearLayoutManager(context)
		btn_question_details_submit.setOnClickListener {
			mPresenter.onSubmitButtonClicked(PreferenceHelper.getCurrentUserId(requireContext()))
		}
	}

	override fun showAnswers(answers: List<Answer>) {
		mAdapter.setDataset(answers)
	}

	override fun notifyItemMoved(fromPosition: Int, toPosition: Int) {
		mAdapter.notifyItemMoved(fromPosition, toPosition)
	}
}