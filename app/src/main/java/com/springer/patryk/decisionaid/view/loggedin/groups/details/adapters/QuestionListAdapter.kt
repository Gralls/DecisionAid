package com.springer.patryk.decisionaid.view.loggedin.groups.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.view.base.BaseRecyclerViewAdapter
import com.springer.patryk.decisionaid.view.loggedin.groups.details.GroupDetailsContract
import com.springer.patryk.decisionaid.view.loggedin.questions.details.QuestionDetailsContract
import kotlinx.android.synthetic.main.row_question_closed.view.*
import kotlinx.android.synthetic.main.row_question_open.view.*

/**
 * Created by Patryk Springer on 27.03.2019.
 */
class QuestionListAdapter(private val mPresenter: GroupDetailsContract.Presenter) :
	BaseRecyclerViewAdapter<Question, QuestionViewHolder>() {

	private val mOpenQuestionType: Int = 0
	private val mClosedQuestionType: Int = 1

	override fun setViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder =
		if (viewType == mOpenQuestionType) {
			val view: View = LayoutInflater.from(parent.context)
				.inflate(R.layout.row_question_open, parent, false)
			OpenQuestionViewHolder(view)
		} else {
			val view: View = LayoutInflater.from(parent.context)
				.inflate(R.layout.row_question_closed, parent, false)
			ClosedQuestionViewHolder(view)
		}

	override fun getItemViewType(position: Int): Int {
		val question = mItems[position]
		return if (question.mIsClosed) mClosedQuestionType else mOpenQuestionType
	}

	override fun bindView(item: Question, viewHolder: QuestionViewHolder) {
		viewHolder.bind(item)
	}

	inner class OpenQuestionViewHolder(val mItemView: View) : QuestionViewHolder(mItemView) {
		override fun bind(question: Question) = with(mItemView) {
			tv_row_question_open_name.text = question.mName
			tv_row_question_open_answers.text = "${question.answerCount}/${question.totalUsers}"
			setOnClickListener {
				mPresenter.onQuestionClicked(question.mId)
			}
		}
	}

	inner class ClosedQuestionViewHolder(val mItemView: View) : QuestionViewHolder(mItemView) {
		override fun bind(question: Question) = with(mItemView) {
			tv_row_question_closed_name.text = question.mName
			tv_row_question_closed_answers.text = "${question.answerCount}/${question.totalUsers}"
			tv_row_question_closed_result.text = question.result?.answer
		}
	}
}

abstract class QuestionViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {
	abstract fun bind(question: Question)
}