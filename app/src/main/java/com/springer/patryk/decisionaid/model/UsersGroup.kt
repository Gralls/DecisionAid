package com.springer.patryk.decisionaid.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 09.03.2019.
 */
data class UsersGroup(
		@SerializedName("id")
		val mId: Int,
		@SerializedName("groupName")
		val mGroupName: String,
		@SerializedName("admin")
		val mAdmin: User?)