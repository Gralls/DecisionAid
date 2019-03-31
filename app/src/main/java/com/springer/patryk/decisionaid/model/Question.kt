package com.springer.patryk.decisionaid.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 27.03.2019.
 */
data class Question(
	@SerializedName("id")
	val mId: Int = -1,
	@SerializedName("name")
	val mName: String = "",
	@SerializedName("answers")
	val mAnswers: List<Answer> = emptyList(),
	@SerializedName("isClosed")
	val mIsClosed: Boolean = false,
	@SerializedName("totalUsers")
	var totalUsers: Int = 0,
	@SerializedName("answerCount")
	var answerCount: Int = 0,
	@SerializedName("result")
	val result: QuestionResult? = null
)
