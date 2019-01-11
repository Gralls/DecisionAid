package com.springer.patryk.decisionaid.view.base

import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.model.network.NetResult
import com.springer.patryk.korkidajmi.view.base.BasePresenter
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BasePresenterImpl(private val mView: BaseFragmentView) : BasePresenter {
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
	} catch (e: IOException) {
		mView.hideNetIndicator()
		handleHttpException(e)
		NetResult.Error(e)
	}
}
