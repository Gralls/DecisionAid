package com.springer.patryk.decisionaid.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Patryk Springer on 27.03.2019.
 */
data class Answer(
	@SerializedName("id")
	val mId: Int = -1,
	@SerializedName("answer")
	var mAnswer: String = ""
)