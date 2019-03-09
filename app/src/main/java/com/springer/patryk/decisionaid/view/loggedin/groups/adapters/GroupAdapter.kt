package com.springer.patryk.decisionaid.view.loggedin.groups.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.UsersGroup
import com.springer.patryk.decisionaid.view.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.row_group.view.*

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class GroupAdapter : BaseRecyclerViewAdapter<UsersGroup, GroupAdapter.ViewHolder>() {

	override fun setViewHolder(parent: ViewGroup): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.row_group, parent, false)
		val holder = ViewHolder(view)
		return holder
	}

	override fun bindView(item: UsersGroup, viewHolder: ViewHolder) {
		viewHolder.bind(item)
	}

	inner class ViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {
		fun bind(group: UsersGroup) = with(mItemView) {
			tv_row_group_name.text = group.mGroupName
		}
	}
}