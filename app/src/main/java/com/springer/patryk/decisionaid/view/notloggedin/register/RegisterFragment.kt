package com.springer.patryk.decisionaid.view.notloggedin.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.extensions.addTextWatcher
import com.springer.patryk.decisionaid.extensions.getStringOrNull
import com.springer.patryk.decisionaid.extensions.stringValue
import com.springer.patryk.decisionaid.model.helpers.PreferenceHelper
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.LoggedInActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class RegisterFragment : BaseFragment(), RegisterContract.View {


	override val layoutResId: Int
		get() = R.layout.fragment_register

	private val mPresenter: RegisterContract.Presenter by lazy { RegisterPresenter(this) }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		addTextWatchers()
		btn_register_submit.setOnClickListener {
			val login = et_register_login.stringValue()
			val name = et_register_name.stringValue()
			val surname = et_register_surname.stringValue()
			val password = et_register_password.stringValue()
			val repeatPassword = et_register_repeat_password.stringValue()
			mPresenter.onRegisterClicked(login, name, surname, password, repeatPassword)
		}
	}

	private fun addTextWatchers() {
		et_register_login.addTextWatcher {
			afterTextChanged { til_register_login.error = null }
		}
		et_register_name.addTextWatcher {
			afterTextChanged { til_register_name.error = null }
		}
		et_register_surname.addTextWatcher {
			afterTextChanged { til_register_surname.error = null }
		}
		et_register_password.addTextWatcher {
			afterTextChanged { til_register_password.error = null }
		}
		et_register_repeat_password.addTextWatcher {
			afterTextChanged { til_register_confirm_password.error = null }
		}
	}

	override fun showLoginError(errorId: Int?) {
		til_register_login.error = context?.getStringOrNull(errorId)
	}

	override fun showNameError(errorId: Int?) {
		til_register_name.error = context?.getStringOrNull(errorId)
	}

	override fun showSurnameError(errorId: Int?) {
		til_register_surname.error = context?.getStringOrNull(errorId)
	}

	override fun showPasswordError(errorId: Int?) {
		til_register_password.error = context?.getStringOrNull(errorId)
	}

	override fun showRepeatPasswordError(errorId: Int?) {
		til_register_confirm_password.error = context?.getStringOrNull(errorId)
	}

	override fun onRegisterCompleted(userId: Int) {
		PreferenceHelper.setCurrentUserId(requireContext(), userId)
		startActivity(Intent(context, LoggedInActivity::class.java))
		mBaseActivity.finish()
	}
}