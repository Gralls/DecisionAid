package com.springer.patryk.decisionaid.view.notloggedin.register

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.decisionaid.model.network.endpoints.UserWS
import com.springer.patryk.decisionaid.view.base.BasePresenterImpl
import com.springer.patryk.decisionaid.view.notloggedin.register.model.RegisterData
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Patryk Springer on 09.03.2019.
 */
class RegisterPresenter(private val mView: RegisterContract.View) : BasePresenterImpl(mView),
		RegisterContract.Presenter {

	private val mUserWs: UserWS by instance()

	override fun onRegisterClicked(login: String, name: String, surname: String, password: String,
								   repeatPassword: String) {
		if (!areInputsValid(login, name, surname, password, repeatPassword)) {
			return
		}
		launch {
			val result = safeApiCall {
				mUserWs.register(RegisterData(name, surname, login, password)).await()
			}
			if (result is NetResult.Success) {
				handleResponse(result.data)
			}
		}
	}

	private fun areInputsValid(login: String, name: String, surname: String, password: String,
							   repeatPassword: String): Boolean {
		var isValid = true

		if (login.isBlank()) {
			isValid = false
			mView.showLoginError(R.string.error_empty_required_field)
		}
		if (name.isBlank()) {
			isValid = false
			mView.showNameError(R.string.error_empty_required_field)
		}
		if (surname.isBlank()) {
			isValid = false
			mView.showSurnameError(R.string.error_empty_required_field)
		}
		if (password.isBlank()) {
			isValid = false
			mView.showPasswordError(R.string.error_empty_required_field)
		}
		if (repeatPassword.isBlank()) {
			isValid = false
			mView.showRepeatPasswordError(R.string.error_empty_required_field)
		}
		if (repeatPassword != password) {
			isValid = false
			mView.showRepeatPasswordError(R.string.error_different_passwords)
		}
		return isValid
	}

	private fun handleResponse(response: Response<User>) {
		when (response.code()) {
			HttpURLConnection.HTTP_CREATED -> mView.onRegisterCompleted(response.body()?.mId ?: -1)
			HttpURLConnection.HTTP_CONFLICT -> mView.showSnackbar(R.string.msg_user_already_exists)
			HttpURLConnection.HTTP_BAD_REQUEST -> mView.showSnackbar(R.string.msg_error_occurred)
		}
	}
}