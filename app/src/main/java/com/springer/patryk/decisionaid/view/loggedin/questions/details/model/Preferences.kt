package com.springer.patryk.decisionaid.view.loggedin.questions.details.model

import com.springer.patryk.decisionaid.model.Answer
import java.io.Serializable

/**
 * Created by Patryk Springer on 06.05.2019.
 */
data class Preferences(
	val mFirstAnswer: Answer, val mSecondAnswer: Answer, var mPreference: Float = 0f
) : Serializable