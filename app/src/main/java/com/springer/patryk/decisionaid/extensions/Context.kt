package com.springer.patryk.decisionaid.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by Patryk Springer on 2019-01-11.
 */

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_LONG) {
	Toast.makeText(this, msg, duration).show()
}

fun Context.showToast(msgId: Int, duration: Int = Toast.LENGTH_LONG) {
	Toast.makeText(this, msgId, duration).show()
}
