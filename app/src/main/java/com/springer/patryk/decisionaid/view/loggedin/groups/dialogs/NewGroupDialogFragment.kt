package com.springer.patryk.decisionaid.view.loggedin.groups.dialogs

import androidx.constraintlayout.widget.ConstraintLayout
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.view.base.BaseDialogFragment

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
}