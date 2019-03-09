package com.springer.patryk.decisionaid.model.helpers

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Patryk Springer on 09.03.2019.
 */
object PreferenceHelper {

	const val NOT_SET_LONG: Long = -1
	const val NOT_SET_INT: Int = -1
	const val NOT_SET_STRING: String = ""
	private const val PREF_NAME: String = "decisionAidPref"
	private const val KEY_USER: String = "user"

	private fun getEditor(context: Context): SharedPreferences.Editor =
		getPreferences(context).edit()

	private fun getPreferences(context: Context): SharedPreferences =
		context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

	fun setCurrentUserId(context: Context, userId: Int) {
		getEditor(context).putInt(KEY_USER, userId).commit()
	}

	fun getCurrentUserId(context: Context): Int =
		getPreferences(context).getInt(KEY_USER, NOT_SET_INT)
}