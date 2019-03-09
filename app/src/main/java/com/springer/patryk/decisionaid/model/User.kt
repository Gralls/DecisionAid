package com.springer.patryk.decisionaid.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 09.03.2019.
 */
data class User(
		@SerializedName("id")
		val mId: Int,
		@SerializedName("name")
		val mName: String,
		@SerializedName("surname")
		val mSurname: String,
		@SerializedName("login")
		val mLogin: String,
		@SerializedName("password")
		val mPassword: String,
		@SerializedName("groups")
		val mGroupsList: List<UsersGroup>)