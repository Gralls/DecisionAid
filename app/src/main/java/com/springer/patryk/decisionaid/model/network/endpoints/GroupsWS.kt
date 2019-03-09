package com.springer.patryk.decisionaid.model.network.endpoints

import com.springer.patryk.decisionaid.model.UsersGroup
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Patryk Springer on 09.03.2019.
 */
interface GroupsWS {

	@GET(EndpointsName.Groups.USER_GROUPS)
	fun getUserGroups(@Path("userId")
					  userId: Int): Deferred<Response<List<UsersGroup>>>
}