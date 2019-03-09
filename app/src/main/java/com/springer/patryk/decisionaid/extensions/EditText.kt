package com.springer.patryk.decisionaid.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Patryk Springer on 09.03.2019.
 */

fun EditText.stringValue(): String = editableText.toString()

inline fun EditText.addTextWatcher(init: CustomTextWatcher.() -> Unit) =
	addTextChangedListener(CustomTextWatcher().apply(init))

class CustomTextWatcher : TextWatcher {
	private var mBeforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
	private var mOnTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
	private var mAfterTextChanged: ((String?) -> Unit)? = null

	override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
		mBeforeTextChanged?.invoke(s, start, count, after)
	}

	override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
		mOnTextChanged?.invoke(s, start, before, count)
	}

	override fun afterTextChanged(s: Editable?) {
		mAfterTextChanged?.invoke(s?.toString())
	}

	fun beforeTextChanged(listener: (CharSequence?, Int, Int, Int) -> Unit) {
		mBeforeTextChanged = listener
	}

	fun onTextChanged(listener: (CharSequence?, Int, Int, Int) -> Unit) {
		mOnTextChanged = listener
	}

	fun afterTextChanged(listener: (String?) -> Unit) {
		mAfterTextChanged = listener
	}
}
