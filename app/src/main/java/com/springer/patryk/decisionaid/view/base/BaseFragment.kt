package com.springer.patryk.decisionaid.view.base

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.springer.patryk.decisionaid.R
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import timber.log.Timber
import java.util.*

abstract class BaseFragment : Fragment(), KodeinAware {

	private var mFragmentSnackbars: ArrayList<Snackbar>? = null
	private var mAutoBindViews = true
	override val kodein: Kodein by closestKodein()
	private var mDialog: AlertDialog? = null
	open val mTitleStringId: Int = -1
	val mBaseActivity: BaseActivity
		get() = activity as BaseActivity

	protected abstract val layoutResId: Int
	var mParentView: BaseFragment? = null
	override fun onAttach(context: Context) {
		Timber.d("onAttach")
		super.onAttach(context)
		if (context !is BaseActivity) {
			throw IllegalArgumentException(
				"BaseFragment must be attached to BaseActivity. " + context.javaClass.name + " does not extends BaseActivity"
			)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		Timber.d("onCreate")
		super.onCreate(savedInstanceState)
		mFragmentSnackbars = ArrayList()
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		Timber.d("onCreateView")
		setHasOptionsMenu(true)
		return inflater.inflate(layoutResId, container, false)
	}

	override fun onResume() {
		Timber.d("onResume")
		super.onResume()
		mBaseActivity.hideKeyboard()
	}

	override fun onPause() {
		Timber.d("onPause")
		for (snackbar in mFragmentSnackbars!!) {
			snackbar.dismiss()
		}
		super.onPause()
	}

	fun setAutoBindViews(autoBindViews: Boolean) {
		mAutoBindViews = autoBindViews
	}

	fun showToast(msgResId: Int) {
		if (!isAdded) {
			return
		}
		showToast(getString(msgResId))
	}

	fun showToast(message: String) {
		if (!isAdded) {
			return
		}
		Toast.makeText(context, message, Toast.LENGTH_LONG).show()
	}

	/**
	 * @see .showFragmentSnackbar
	 */
	fun showFragmentSnackbar(msgResId: Int) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(getString(msgResId))
	}

	/**
	 * @see .showFragmentSnackbar
	 */
	fun showFragmentSnackbar(msgResId: Int, durationMs: Int) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(getString(msgResId), durationMs)
	}

	/**
	 * @see .showFragmentSnackbar
	 */
	fun showFragmentSnackbar(msg: String) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(msg, null, null)
	}

	/**
	 * @see .showFragmentSnackbar
	 */
	fun showFragmentSnackbar(msg: String, durationMs: Int) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(msg, null, null, durationMs)
	}

	/**
	 * @see .showFragmentSnackbar
	 */
	fun showFragmentSnackbar(
		msgResId: Int, actionMsgResId: Int, actionCallback: View.OnClickListener
	) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(
			getString(msgResId), getString(actionMsgResId), actionCallback, Snackbar.LENGTH_LONG
		)
	}

	/**
	 * @see .showFragmentSnackbar
	 */
	fun showFragmentSnackbar(
		msgResId: Int, actionMsgResId: Int, actionCallback: View.OnClickListener, durationMs: Int
	) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(
			getString(msgResId), getString(actionMsgResId), actionCallback, durationMs
		)
	}

	/**
	 * Shows snackbar that will be dismissed on fragment replacement
	 */
	fun showFragmentSnackbar(
		msg: String, actionMsg: String?, actionCallback: View.OnClickListener?
	) {
		if (!isAdded) {
			return
		}
		showFragmentSnackbar(msg, actionMsg, actionCallback, Snackbar.LENGTH_LONG)
	}

	/**
	 * Shows snackbar that will be dismissed on fragment replacement
	 */
	fun showFragmentSnackbar(
		msg: String, actionMsg: String?, actionCallback: View.OnClickListener?, durationMs: Int
	) {
		if (!isAdded) {
			return
		}
		val snackbar =
			Snackbar.make(mBaseActivity.findViewById(android.R.id.content), msg, durationMs)
		with(snackbar.view) {
			val snackbarTextId = R.id.snackbar_text
			val textView = this.findViewById(snackbarTextId) as TextView
			textView.setTextColor(Color.WHITE)
			this.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

		}
		if (actionCallback != null) {
			snackbar.setAction(actionMsg, actionCallback).show()
		}
		snackbar.addCallback(object : Snackbar.Callback() {
			override fun onDismissed(snackbar: Snackbar?, event: Int) {
				super.onDismissed(snackbar, event)
				mFragmentSnackbars!!.remove(snackbar)
			}
		})
		mFragmentSnackbars!!.add(snackbar)
		snackbar.show()
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(msgResId: Int) {
		if (!isAdded) {
			return
		}
		showSnackbar(getString(msgResId))
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(msgResId: Int, durationMs: Int) {
		if (!isAdded) {
			return
		}
		showSnackbar(getString(msgResId), durationMs)
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(msg: String) {
		if (!isAdded) {
			return
		}
		showSnackbar(msg, null, null)
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(msg: String, durationMs: Int) {
		if (!isAdded) {
			return
		}
		showSnackbar(msg, null, null, durationMs)
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(msgResId: Int, actionMsgResId: Int, actionCallback: View.OnClickListener) {
		if (!isAdded) {
			return
		}
		showSnackbar(getString(msgResId), getString(actionMsgResId), actionCallback)
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(
		msgResId: Int, actionMsgResId: Int, actionCallback: View.OnClickListener, durationMs: Int
	) {
		if (!isAdded) {
			return
		}
		showSnackbar(getString(msgResId), getString(actionMsgResId), actionCallback, durationMs)
	}

	/**
	 * @see .showSnackbar
	 */
	fun showSnackbar(msg: String, actionMsg: String?, actionCallback: View.OnClickListener?) {
		if (!isAdded) {
			return
		}
		showSnackbar(msg, actionMsg, actionCallback, Snackbar.LENGTH_LONG)
	}

	/**
	 * Shows snackbar that will live longer then fragment
	 */
	fun showSnackbar(
		msg: String, actionMsg: String?, actionCallback: View.OnClickListener?, durationMs: Int
	) {
		if (!isAdded) {
			return
		}
		val snackbar = Snackbar.make(view!!, msg, durationMs)
		with(snackbar.view) {
			val snackbarTextId = R.id.snackbar_text
			val textView = this.findViewById(snackbarTextId) as TextView
			textView.setTextColor(Color.WHITE)
			this.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

		}
		if (actionCallback != null) {
			snackbar.setAction(actionMsg, actionCallback).show()
		}
		snackbar.show()
	}

	fun showNetIndicator() {
		if (mDialog == null) {
			val builder: AlertDialog.Builder = AlertDialog.Builder(context)
			val alert =
				LayoutInflater.from(context).inflate(R.layout.dialog_net_indicator, null, false)
			builder.setView(alert)
			builder.setCancelable(false)
			mDialog = builder.create()
			mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		}
		mDialog?.show()
	}

	fun hideNetIndicator() {
		mDialog?.hide()
	}
}