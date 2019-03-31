package com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.extensions.addTextWatcher
import com.springer.patryk.decisionaid.extensions.stringValue
import com.springer.patryk.decisionaid.view.base.BaseDialogFragment
import com.springer.patryk.decisionaid.view.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_add_new_member.*

/**
 * Created by Patryk Springer on 31.03.2019.
 */
class AddMemberDialogFragment : BaseDialogFragment(), AddMemberContract.View {

	override val layoutResId: Int = R.layout.dialog_add_new_member
	private val mPresenter: AddMemberContract.Presenter by lazy { AddMemberPresenter(this) }

	companion object {
		private const val GROUP_ID_KEY = "groupId"
		fun newInstance(
			groupId: Int, targetFragment: BaseFragment, reqCode: Int
		): AddMemberDialogFragment {
			val fragment = AddMemberDialogFragment()
			val bundle = Bundle()
			bundle.putInt(GROUP_ID_KEY, groupId)
			fragment.arguments = bundle
			fragment.setTargetFragment(targetFragment, reqCode)
			return fragment
		}
	}

	override fun onStart() {
		super.onStart()
		dialog.window.setLayout(
			ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
		)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		et_new_member_login.addTextWatcher {
			afterTextChanged { til_new_member_login.error = null }
		}

		btn_new_member_submit.setOnClickListener {
			arguments?.let { bundle ->
				val groupId: Int = bundle.getInt(GROUP_ID_KEY)
				mPresenter.onSubmitClicked(groupId, et_new_member_login.stringValue())
			}
		}
	}

	override fun showLoginError(errorId: Int) {
		til_new_member_login.error = getString(errorId)
	}

	override fun onMemberAdded() {
		dismiss()
		targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, Intent())
	}
}