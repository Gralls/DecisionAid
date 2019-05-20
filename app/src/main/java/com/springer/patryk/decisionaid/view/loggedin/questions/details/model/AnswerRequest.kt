package com.springer.patryk.decisionaid.view.loggedin.questions.details.model

import com.google.gson.annotations.SerializedName
import com.springer.patryk.decisionaid.model.Answer

/**
 * Created by Patryk Springer on 04.04.2019.
 */
data class AnswerRequest(
	@SerializedName("answers")
	val mAnswers: List<Preferences> = emptyList()
)