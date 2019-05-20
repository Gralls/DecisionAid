package com.springer.patryk.decisionaid.view.loggedin.questions.details.preferenceanswer

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.view.base.BaseFragment
import com.springer.patryk.decisionaid.view.loggedin.questions.details.model.Preferences
import kotlinx.android.synthetic.main.fragment_question_answer.*

/**
 * Created by Patryk Springer on 28.04.2019.
 */
class QuestionPreferenceAnswersFragment : BaseFragment() {

	override val layoutResId: Int = R.layout.fragment_question_answer

	companion object {
		const val PREFERENCE_KEY: String = "preference"
		fun newInstance(preferences: Preferences): QuestionPreferenceAnswersFragment {
			val fragment: QuestionPreferenceAnswersFragment = QuestionPreferenceAnswersFragment()
			val bundle = Bundle()
			bundle.putSerializable(PREFERENCE_KEY, preferences)
			fragment.arguments = bundle
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val preferences: Preferences = arguments?.get(PREFERENCE_KEY) as Preferences
		tv_question_second_answer.text = preferences.mSecondAnswer.mAnswer
		tv_question_first_answer.text = preferences.mFirstAnswer.mAnswer
		et_question_preference_value.setText(preferences.mPreference.toString())
		sb_question_preference.max = 100
		sb_question_preference.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
			override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
				preferences.mPreference = progress / 100f
				et_question_preference_value.setText(preferences.mPreference.toString())
			}

			override fun onStartTrackingTouch(seekBar: SeekBar?) {}

			override fun onStopTrackingTouch(seekBar: SeekBar?) {}
		})

	}
}