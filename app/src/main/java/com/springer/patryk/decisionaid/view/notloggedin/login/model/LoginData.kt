package com.springer.patryk.decisionaid.view.notloggedin.login.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 09.03.2019.
 */
data class LoginData(
		@SerializedName("login")
		val mLogin: String,
		@SerializedName("password")
		val mPassword: String)