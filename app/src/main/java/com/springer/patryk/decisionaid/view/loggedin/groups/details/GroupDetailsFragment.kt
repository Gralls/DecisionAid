package com.springer.patryk.decisionaid.view.loggedin.groups.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.LoggedInActivity
import com.springer.patryk.decisionaid.view.loggedin.groupmembers.GroupMembersFragment
import com.springer.patryk.decisionaid.view.loggedin.groups.details.adapters.QuestionListAdapter
import com.springer.patryk.decisionaid.view.loggedin.questions.details.QuestionDetailsFragment
import com.springer.patryk.decisionaid.view.loggedin.questions.newquestion.NewQuestionFragment
import kotlinx.android.synthetic.main.fragment_group_details.*

/**
 * Created by Patryk Springer on 10.03.2019.
 */
class GroupDetailsFragment : BaseFragment(), GroupDetailsContract.View {

	override val layoutResId: Int
		get() = R.layout.fragment_group_details
	private val mPresenter: GroupDetailsContract.Presenter by lazy {
		GroupDetailsPresenter(
			this
		)
	}
	private val mAdapter: QuestionListAdapter by lazy { QuestionListAdapter(mPresenter) }

	companion object {
		const val NEW_QUESTION_REQ_CODE = 94721
		fun newInstance(groupId: Int): GroupDetailsFragment {
			val fragment = GroupDetailsFragment()
			val bundle = Bundle().apply {
				putInt(GROUP_ID_KEY, groupId)
			}
			fragment.arguments = bundle
			return fragment
		}

		const val GROUP_ID_KEY: String = "groupId"
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		(mBaseActivity as LoggedInActivity).setBottomFabAction(R.drawable.ic_add) {
			arguments?.let {
				val groupId = it.getInt(GROUP_ID_KEY)
				NewQuestionFragment.newInstance(groupId, this, NEW_QUESTION_REQ_CODE)
					.show(fragmentManager, "dialog")
			}
		}
		rv_questions_list.adapter = mAdapter
		rv_questions_list.layoutManager = LinearLayoutManager(context)
	}

	override fun onResume() {
		super.onResume()
		refreshView()
	}

	private fun refreshView() {
		arguments?.let {
			val id = it.getInt(GROUP_ID_KEY)
			val userId = PreferenceHelper.getCurrentUserId(requireContext())
			mPresenter.refreshQuestionList(userId, id)
			mPresenter.refreshGroupDetails(id)
		}
	}

	override fun refreshQuestionList(questions: List<Question>) {
		mAdapter.setDataset(questions)
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		menu?.clear()
		inflater?.inflate(R.menu.group_details_menu, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item?.itemId) {
			R.id.menu_members_list -> {
				mPresenter.onMembersListClicked()
			}
		}
		return false
	}

	override fun openMembersList(membersList: List<User>) {
		arguments?.let {
			val groupId = it.getInt(GROUP_ID_KEY)
			mBaseActivity.setChildContent(GroupMembersFragment.newInstance(groupId))
		}
	}

	override fun setGroupName(name: String) {
		tv_group_name.text = name
	}

	override fun setGroupAdmin(adminName: String, adminSurname: String) {
		tv_group_admin.text = "$adminName $adminSurname"
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == NEW_QUESTION_REQ_CODE && resultCode == Activity.RESULT_OK) {
			refreshView()
		}
	}

	override fun openQuestionDetails(questionId: Int) {
		mBaseActivity.setChildContent(QuestionDetailsFragment.newInstance(questionId))
	}
}