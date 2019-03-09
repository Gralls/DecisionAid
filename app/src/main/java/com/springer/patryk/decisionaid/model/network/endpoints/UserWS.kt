package com.springer.patryk.decisionaid.model.network.endpoints

import com.springer.patryk.decisionaid.model.User
import com.springer.patryk.decisionaid.view.notloggedin.login.model.LoginData
import com.springer.patryk.decisionaid.view.notloggedin.register.model.RegisterData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Patryk Springer on 09.03.2019.
 */
interface UserWS {

	@POST(EndpointsName.User.LOGIN)
	fun login(@Body
			  login: LoginData): Deferred<Response<User>>

	@POST(EndpointsName.User.REGISTER)
	fun register(@Body
				 registerData: RegisterData): Deferred<Response<User>>
}