package com.springer.patryk.decisionaid.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Patryk Springer on 2019-01-11.
 */

fun View.showSnackbar(msg: CharSequence, duration: Int = Snackbar.LENGTH_LONG) {
	Snackbar.make(this, msg, duration).show()
}

fun View.showSnackbar(msgId: Int, duration: Int = Snackbar.LENGTH_LONG) {
	Snackbar.make(this, msgId, duration).show()
}

fun View.gone() {
	visibility = View.GONE
}

fun View.invisible() {
	visibility = View.INVISIBLE
}

fun View.visible() {
	visibility = View.VISIBLE
}