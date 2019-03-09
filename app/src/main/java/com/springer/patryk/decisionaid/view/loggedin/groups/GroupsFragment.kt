package com.springer.patryk.decisionaid.view.loggedin.groups

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.UsersGroup
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.groups.adapters.GroupAdapter
import kotlinx.android.synthetic.main.fragment_groups.*

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class GroupsFragment : GroupsContract.View, BaseFragment() {

	override val layoutResId: Int
		get() = R.layout.fragment_groups

	private val mPresenter: GroupsContract.Presenter by lazy { GroupsPresenter(this) }
	private val mAdapter: GroupAdapter by lazy { GroupAdapter() }
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		rv_groups_list.adapter = mAdapter
		rv_groups_list.layoutManager = GridLayoutManager(context, 2)
	}

	override fun onResume() {
		super.onResume()
		mPresenter.refreshGroups(PreferenceHelper.getCurrentUserId(requireContext()))
	}

	override fun showGroupList(groups: List<UsersGroup>) {
		mAdapter.setDataset(groups)
	}

	override fun showEmptyList() {
	}
}