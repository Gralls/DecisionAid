package com.springer.patryk.decisionaid.view.loggedin.groups.details.model

import com.google.gson.annotations.SerializedName
import com.springer.patryk.decisionaid.model.User

/**
 * Created by Patryk Springer on 29.03.2019.
 */
data class GroupDetails(
	@SerializedName("id")
	val id: Int,
	@SerializedName("admin")
	val admin: User?,
	@SerializedName("name")
	val name: String,
	@SerializedName("members")
	val members: List<User>

)