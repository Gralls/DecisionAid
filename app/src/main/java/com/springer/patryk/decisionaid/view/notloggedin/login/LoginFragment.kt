package com.springer.patryk.decisionaid.view.notloggedin.login

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
import com.springer.patryk.decisionaid.view.notloggedin.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by Patryk Springer on 2019-01-11.
 */
class LoginFragment : BaseFragment(), LoginContract.View {

	override val layoutResId: Int
		get() = R.layout.fragment_login
	private val mPresenter: LoginContract.Presenter by lazy { LoginPresenter(this) }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		addTextWatchers()
		btn_login_register.setOnClickListener {
			mBaseActivity.setChildContent(RegisterFragment())
		}

		btn_login_submit.setOnClickListener {
			mPresenter.onLoginClicked(et_login_field.stringValue(), et_password_field.stringValue())
		}
	}

	private fun addTextWatchers() {
		et_login_field.addTextWatcher {
			afterTextChanged { til_login.error = null }
		}
		et_password_field.addTextWatcher {
			afterTextChanged { til_password.error = null }
		}
	}

	override fun onLoginSuccess(userId: Int) {
		PreferenceHelper.setCurrentUserId(requireContext(), userId)
		startActivity(Intent(context, LoggedInActivity::class.java))
		mBaseActivity.finish()
	}

	override fun setLoginError(errorId: Int?) {
		til_login.error = context?.getStringOrNull(errorId)
	}

	override fun setPasswordError(errorId: Int?) {
		til_password.error = context?.getStringOrNull(errorId)
	}
}