package com.springer.patryk.decisionaid.extensions

import android.support.design.widget.Snackbar
import android.view.View

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