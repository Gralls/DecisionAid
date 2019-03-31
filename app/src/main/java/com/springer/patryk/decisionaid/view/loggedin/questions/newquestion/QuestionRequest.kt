package com.springer.patryk.decisionaid.view.loggedin.questions.newquestion

import com.google.gson.annotations.SerializedName
import com.springer.patryk.decisionaid.model.Question

/**
 * Created by Patryk Springer on 31.03.2019.
 */
data class QuestionRequest(
	@SerializedName("question")
	val mQuestion: Question
)