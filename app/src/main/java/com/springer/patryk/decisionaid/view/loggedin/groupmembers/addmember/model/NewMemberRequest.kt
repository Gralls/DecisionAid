package com.springer.patryk.decisionaid.view.loggedin.groupmembers.addmember.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 31.03.2019.
 */
data class NewMemberRequest(
	@SerializedName("userLogin")
	val mLogin: String
)