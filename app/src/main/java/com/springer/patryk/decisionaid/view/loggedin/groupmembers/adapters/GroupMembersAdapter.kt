package com.springer.patryk.decisionaid.view.loggedin.groupmembers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.view.base.BaseRecyclerViewAdapter
import com.springer.patryk.decisionaid.view.loggedin.groupmembers.GroupMembersContract
import kotlinx.android.synthetic.main.row_group_member.view.*

/**
 * Created by Patryk Springer on 31.03.2019.
 */
class GroupMembersAdapter(private val mPresenter: GroupMembersContract.Presenter) :
	BaseRecyclerViewAdapter<User, GroupMembersAdapter.ViewHolder>() {

	override fun setViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.row_group_member, parent, false)
		val viewHolder = ViewHolder(view)
		viewHolder.itemView.iv_row_member_delete.setOnClickListener {
			val position = viewHolder.adapterPosition
			mPresenter.onMemberRemoved(position)
			notifyItemRemoved(position)
		}
		return viewHolder
	}

	override fun bindView(item: User, viewHolder: ViewHolder) {
		viewHolder.bind(item)
	}

	inner class ViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {
		fun bind(user: User) = with(mItemView) {
			tv_row_member_name.text = user.mName
			tv_row_member_surname.text = user.mSurname
		}
	}
}