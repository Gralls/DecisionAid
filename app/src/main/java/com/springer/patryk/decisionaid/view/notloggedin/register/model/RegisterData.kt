package com.springer.patryk.decisionaid.view.notloggedin.register.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 09.03.2019.
 */
data class RegisterData(
		@SerializedName("name")
		val name: String, val surname: String = "", val login: String = "",
		val password: String = "")