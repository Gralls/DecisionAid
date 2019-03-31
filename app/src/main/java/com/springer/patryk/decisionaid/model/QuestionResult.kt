package com.springer.patryk.decisionaid.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 29.03.2019.
 */
data class QuestionResult(
	@SerializedName("id")
	val id: Int = 0,
	@SerializedName("answer")
	val answer: String = "",
	@SerializedName("preferences")
	val answerPref: Float = 0f
)