package com.springer.patryk.decisionaid.view.loggedin.questions.newquestion

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.extensions.stringValue
import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseDialogFragment
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.questions.newquestion.adapters.AnswerListAdapter
import kotlinx.android.synthetic.main.fragment_new_question.*

/**
 * Created by Patryk Springer on 29.03.2019.
 */
class NewQuestionFragment : BaseDialogFragment(), NewQuestionContract.View {

	override val layoutResId: Int = R.layout.fragment_new_question
	private val mPresenter: NewQuestionContract.Presenter by lazy { NewQuestionPresenter(this) }
	private val mAdapter: AnswerListAdapter by lazy { AnswerListAdapter(mPresenter) }
	override fun onStart() {
		super.onStart()
		dialog?.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
	}

	companion object {
		private const val GROUP_KEY = "groupId"
		fun newInstance(groupId: Int, parent: BaseFragment, reqCode: Int): NewQuestionFragment {
			val fragment = NewQuestionFragment()
			val bundle = Bundle()
			bundle.putInt(GROUP_KEY, groupId)
			fragment.arguments = bundle
			fragment.setTargetFragment(parent, reqCode)
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		rv_new_question_answers.adapter = mAdapter
		rv_new_question_answers.layoutManager = LinearLayoutManager(context)
		fab_new_question_add_answer.setOnClickListener { mPresenter.onAddClicked() }
		btn_new_question_submit.setOnClickListener {
			val userId = PreferenceHelper.getCurrentUserId(requireContext())
			val groupId = arguments?.getInt(GROUP_KEY)
			val question: String = et_new_question_name.stringValue()
			mPresenter.onSendQuestionClicked(userId, groupId, question)
		}
	}

	override fun showAnswers(list: List<Answer>) {
		mAdapter.setDataset(list)
	}

	override fun onDismiss(dialog: DialogInterface?) {
		super.onDismiss(dialog)
		targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, Intent())
	}
}