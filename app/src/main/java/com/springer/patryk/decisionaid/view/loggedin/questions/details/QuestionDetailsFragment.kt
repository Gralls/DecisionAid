package com.springer.patryk.decisionaid.view.loggedin.questions.details

import android.os.Bundle
import android.view.View
import com.marcinmoskala.kotlinandroidviewbindings.bindToText
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.base.viewpager.BaseViewPagerFragment
import com.springer.patryk.decisionaid.view.loggedin.LoggedInActivity
import com.springer.patryk.decisionaid.view.loggedin.questions.details.adapters.AnswersListAdapter
import com.springer.patryk.decisionaid.view.loggedin.questions.details.model.Preferences
import com.springer.patryk.decisionaid.view.loggedin.questions.details.preferenceanswer.QuestionPreferenceAnswersFragment
import kotlinx.android.synthetic.main.fragment_question_details.*

/**
 * Created by Patryk Springer on 04.04.2019.
 */
class QuestionDetailsFragment : BaseViewPagerFragment(), QuestionDetailsContract.View {

	override val mListOfFragments: MutableList<BaseFragment> = mutableListOf()

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

		btn_question_details_submit.setOnClickListener {
			mPresenter.onSubmitButtonClicked(PreferenceHelper.getCurrentUserId(requireContext()))
		}
	}

	override fun showAnswers(answers: List<Preferences>) {
		answers.forEach {
			mListOfFragments.add(QuestionPreferenceAnswersFragment.newInstance(it))
		}
		notifyAdapter()
	}

	override fun notifyItemMoved(fromPosition: Int, toPosition: Int) {
		mAdapter.notifyItemMoved(fromPosition, toPosition)
	}
}