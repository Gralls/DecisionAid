package com.springer.patryk.decisionaid.view.loggedin.questions.details

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Patryk Springer on 04.04.2019.
 */
class AnswerTouchHelperCallback(private val mOnItemMovedCallback: OnItemTouchListener) :
	ItemTouchHelper.SimpleCallback(
		ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), ItemTouchHelper.ACTION_STATE_DRAG
	) {

	override fun isLongPressDragEnabled(): Boolean = true

	override fun isItemViewSwipeEnabled(): Boolean = false

	override fun onMove(
		recyclerView: RecyclerView,
		viewHolder: RecyclerView.ViewHolder,
		target: RecyclerView.ViewHolder
	): Boolean {
		mOnItemMovedCallback.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
		return true
	}

	override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
	}
}