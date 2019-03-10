package com.springer.patryk.decisionaid.view.loggedin.groups.dialogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.extensions.addTextWatcher
import com.springer.patryk.decisionaid.extensions.stringValue
import com.springer.patryk.decisionaid.view.base.BaseDialogFragment
import com.springer.patryk.decisionaid.view.loggedin.groups.GroupsFragment
import kotlinx.android.synthetic.main.dialog_add_new_group.*

/**
 * Created by Patryk Springer on 10.03.2019.
 */

class NewGroupDialogFragment : BaseDialogFragment() {

    companion object {
        const val TAG: String = "NewGroupDialog"
    }

    override val layoutResId: Int
        get() = R.layout.dialog_add_new_group

    override fun onStart() {
        super.onStart()
        dialog.window.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
    }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		et_new_group_name.addTextWatcher {
			afterTextChanged { til_new_group_name.error = null }
		}
		btn_new_group_submit.setOnClickListener {
			val groupName = et_new_group_name.stringValue()
			if (groupName.isBlank()) {
				til_new_group_name.error = getString(R.string.error_empty_required_field)
			} else {
				val intent: Intent = Intent().apply {
					putExtra(GroupsFragment.NEW_GROUP_KEY, groupName)
				}

				targetFragment?.onActivityResult(GroupsFragment.NEW_GROUP_CODE, Activity.RESULT_OK,
						intent)
				dismiss()
			}
		}
	}
}