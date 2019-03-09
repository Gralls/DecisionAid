package com.springer.patryk.decisionaid.view.base

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.korkidajmi.view.base.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.coroutines.CoroutineContext

abstract class BasePresenterImpl(private val mView: BaseFragmentView) : BasePresenter, KodeinAware,
		CoroutineScope {

	override val kodein: Kodein by closestKodein(mView.getContext()!!)
	var mJob: Job = Job()

	override val coroutineContext: CoroutineContext = mJob + Dispatchers.Main
	override fun handleHttpException(e: Exception) {
		when (e) {
			is SocketTimeoutException -> {
				mView.showSnackbar(R.string.net_error_service_unavailable)
			}
			is ConnectException -> {
				mView.showSnackbar(R.string.net_error_no_internet_connection)
			}
		}
	}

	override suspend fun <T : Any> safeApiCall(call: suspend () -> T): NetResult<T> = try {
		mView.showNetIndicator()
		val result = call.invoke()
		mView.hideNetIndicator()
		NetResult.Success(result)
	} catch (e: java.lang.Exception) {
		mView.hideNetIndicator()
		handleHttpException(e)
		NetResult.Error(e)
	}
}
