package com.springer.patryk.decisionaid.view.loggedin.questions.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.view.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.row_question_answer.view.*

/**
 * Created by Patryk Springer on 04.04.2019.
 */
class AnswersListAdapter : BaseRecyclerViewAdapter<Answer, AnswersListAdapter.ViewHolder>() {

	override fun setViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.row_question_answer, parent, false)
		return ViewHolder(view)
	}

	override fun bindView(item: Answer, viewHolder: ViewHolder) {
		viewHolder.bind(item)
	}

	inner class ViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {
		fun bind(answer: Answer) = with(mItemView) {
			tv_row_question_answer.text = answer.mAnswer
		}
	}
}