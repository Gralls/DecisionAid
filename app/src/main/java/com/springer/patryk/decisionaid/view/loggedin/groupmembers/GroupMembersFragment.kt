package com.springer.patryk.decisionaid.view.loggedin.groupmembers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.LoggedInActivity
import com.springer.patryk.decisionaid.view.loggedin.groupmembers.adapters.GroupMembersAdapter
import com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember.AddMemberDialogFragment
import kotlinx.android.synthetic.main.fragment_group_members.*

/**
 * Created by Patryk Springer on 29.03.2019.
 */
class GroupMembersFragment : BaseFragment(), GroupMembersContract.View {

	override val layoutResId: Int = R.layout.fragment_group_members
	private val mPresenter: GroupMembersContract.Presenter  by lazy { GroupMembersPresenter(this) }
	private val mAdapter: GroupMembersAdapter by lazy { GroupMembersAdapter(mPresenter) }

	companion object {
		const val GROUP_ID_KEY = "groupId"
		const val NEW_MEMBER_REQ_CODE = 8912
		fun newInstance(groupId: Int): GroupMembersFragment {
			val fragment = GroupMembersFragment()
			val bundle = Bundle()
			bundle.putInt(GROUP_ID_KEY, groupId)
			fragment.arguments = bundle
			return fragment
		}
	}

	override fun onResume() {
		super.onResume()
		refreshView()
	}

	private fun refreshView() {
		arguments?.let { bundle ->
			mPresenter.refreshMembers(bundle.getInt(GROUP_ID_KEY))
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		rv_group_members_list.adapter = mAdapter
		rv_group_members_list.layoutManager = LinearLayoutManager(context)

		(mBaseActivity as LoggedInActivity).setBottomFabAction(R.drawable.ic_person_add) {
			mPresenter.onAddNewMemberClicked()
		}
	}

	override fun showMembersList(membersList: List<User>) {
		mAdapter.setDataset(membersList)
	}

	override fun showNewMemberDialog(groupId: Int) {
		AddMemberDialogFragment.newInstance(groupId, this, NEW_MEMBER_REQ_CODE)
			.show(fragmentManager, "dialog")
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == NEW_MEMBER_REQ_CODE && resultCode == Activity.RESULT_OK) {
			refreshView()
		}
	}
}