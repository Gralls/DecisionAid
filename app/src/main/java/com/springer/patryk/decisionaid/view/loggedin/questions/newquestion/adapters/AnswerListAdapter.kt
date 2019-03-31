package com.springer.patryk.decisionaid.view.loggedin.questions.newquestion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.extensions.addTextWatcher
import com.springer.patryk.decisionaid.model.Answer
import com.springer.patryk.decisionaid.view.base.BaseRecyclerViewAdapter
import com.springer.patryk.decisionaid.view.loggedin.questions.newquestion.NewQuestionContract
import kotlinx.android.synthetic.main.row_answer.view.*

/**
 * Created by Patryk Springer on 29.03.2019.
 */
class AnswerListAdapter(private val mPresenter: NewQuestionContract.Presenter) :
	BaseRecyclerViewAdapter<Answer, AnswerListAdapter.ViewHolder>() {

	override fun setViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View =
			LayoutInflater.from(parent.context).inflate(R.layout.row_answer, parent, false)
		val viewHolder = ViewHolder(view)
		viewHolder.itemView.iv_row_answer_remove.setOnClickListener {
			val position = viewHolder.adapterPosition
			mPresenter.onAnswerRemoved(position)
			notifyItemRemoved(position)
		}
		viewHolder.itemView.et_row_answer.addTextWatcher {
			afterTextChanged {
				mItems[viewHolder.adapterPosition].mAnswer = it ?: ""
			}
		}
		return viewHolder
	}

	override fun bindView(item: Answer, viewHolder: ViewHolder) {
		viewHolder.bind(item)
	}

	inner class ViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {
		fun bind(answer: Answer) = with(mItemView) {
			et_row_answer.setText(answer.mAnswer)
		}
	}
}