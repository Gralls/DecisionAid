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
			const val GROUP_DETAILS = "groups/{groupId}"
		}
	}

	class Question {
		companion object {
			const val USERS_QUESTIONS = "users/{userId}/questions"
			const val QUESTION_DETAILS = "questions/{questionId}"
			const val NEW_QUESTION = "users/{userId}/groups/{groupId}"
		}
	}
}