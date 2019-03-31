package com.springer.patryk.decisionaid.model.network.endpoints

import com.springer.patryk.decisionaid.model.Question
import com.springer.patryk.decisionaid.view.loggedin.questions.newquestion.QuestionRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Patryk Springer on 27.03.2019.
 */
interface QuestionWS {

	@GET(EndpointsName.Question.USERS_QUESTIONS)
	fun getQuestionList(
		@Path("userId")
		userId: Int,
		@Query("groupId")
		groupId: Int
	): Deferred<Response<List<Question>>>

	@DELETE(EndpointsName.Question.QUESTION_DETAILS)
	fun closeQuestion(
		@Path("questionId")
		questionId: Int
	)

	@POST(EndpointsName.Question.NEW_QUESTION)
	fun addQuestion(
		@Path("userId")
		userId: Int,
		@Path("groupId")
		groupId: Int?,
		@Body
		question: QuestionRequest
	): Deferred<Response<Question>>
}