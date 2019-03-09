package com.springer.patryk.decisionaid.view.notloggedin.login

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.UserWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.notloggedin.login.model.LoginData
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Patryk Springer on 2019-01-11.
 */
class LoginPresenter(private val mView: LoginContract.View) : LoginContract.Presenter,
		BasePresenterImpl(mView) {

	private val mUserWS: UserWS by instance()

	override fun onLoginClicked(login: String, password: String) {
		if (!validateInputs(login, password)) {
			return
		}
		launch {
			val result = safeApiCall { mUserWS.login(LoginData(login, password)).await() }
			if (result is NetResult.Success) {
				val response = result.data
				handleResponse(response)
			}
		}
	}

	private fun validateInputs(login: String, password: String): Boolean {
		var isValid = true
		if (login.isBlank()) {
			isValid = false
			mView.setLoginError(R.string.error_empty_required_field)
		}
		if (password.isBlank()) {
			isValid = false
			mView.setPasswordError(R.string.error_empty_required_field)
		}
		return isValid
	}

	private fun handleResponse(response: Response<User>) {
		when (response.code()) {
			HttpURLConnection.HTTP_OK -> mView.onLoginSuccess(response.body()?.mId ?: -1)
			HttpURLConnection.HTTP_NOT_FOUND -> mView.showSnackbar(R.string.msg_invalid_login_data)
		}
	}
}