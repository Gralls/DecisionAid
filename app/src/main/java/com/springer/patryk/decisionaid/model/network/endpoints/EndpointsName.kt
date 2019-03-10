package com.springer.patryk.decisionaid.model.network.endpoints

class EndpointsName {
	class User {
		companion object {
			const val LOGIN = "login"
			const val REGISTER = "users"
		}
	}

	class Groups {
		companion object {
			const val USER_GROUPS = "users/{userId}/groups"
			const val GROUPS = "groups"
		}
	}
}