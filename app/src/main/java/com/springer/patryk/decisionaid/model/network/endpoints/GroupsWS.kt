package com.springer.patryk.decisionaid.model.network.endpoints

import com.springer.patryk.decisionaid.model.UsersGroup
import com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember.model.NewMemberRequest
import com.springer.patryk.decisionaid.view.loggedin.groups.details.model.GroupDetails
import com.springer.patryk.decisionaid.view.loggedin.groups.model.NewGroup
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Patryk Springer on 09.03.2019.
 */
interface GroupsWS {

	@GET(EndpointsName.Groups.USER_GROUPS)
	fun getUserGroups(
		@Path("userId")
		userId: Int
	): Deferred<Response<List<UsersGroup>>>

	@POST(EndpointsName.Groups.GROUPS)
	fun createNewGroup(
		@Body
		group: NewGroup
	): Deferred<Response<UsersGroup>>

	@GET(EndpointsName.Groups.GROUP_DETAILS)
	fun getGroupDetails(
		@Path("groupId")
		groupId: Int
	): Deferred<Response<GroupDetails>>

	@DELETE(EndpointsName.Groups.REMOVE_GROUP_MEMBER)
	fun removeMemberFromGroup(
		@Path("groupId")
		groupId: Int,
		@Path("userId")
		userId: Int
	): Deferred<Response<Unit>>

	@POST(EndpointsName.Groups.GROUP_DETAILS)
	fun assignUserToGroup(
		@Path("groupId")
		groupId: Int,
		@Body
		login: NewMemberRequest
	): Deferred<Response<Unit>>
}