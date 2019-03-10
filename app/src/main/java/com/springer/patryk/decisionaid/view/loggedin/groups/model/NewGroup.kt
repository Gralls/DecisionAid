package com.springer.patryk.decisionaid.view.loggedin.groups.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 10.03.2019.
 */
data class NewGroup(
		@SerializedName("name")
		val mGroupName: String,
		@SerializedName("admin")
		val mAdminId: Int)