package com.springer.patryk.decisionaid.view.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> :
	androidx.recyclerview.widget.RecyclerView.Adapter<VH>() {

	protected var mItems: List<T> = emptyList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
		return setViewHolder(parent, viewType)
	}

	override fun onBindViewHolder(holder: VH, position: Int) {
		bindView(mItems[position], holder)
	}

	override fun getItemCount(): Int = mItems.size

	protected abstract fun setViewHolder(parent: ViewGroup, viewType: Int): VH
	protected abstract fun bindView(item: T, viewHolder: VH)

	open fun setDataset(items: List<T>) {
		this.mItems = items
		notifyDataSetChanged()
	}
}