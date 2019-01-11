package com.springer.patryk.korkidajmi.view.base

import com.springer.patryk.decisionaid.model.network.NetResult

interface BasePresenter {
	fun handleHttpException(e: Exception)
	suspend fun <T : Any> safeApiCall(call: suspend () -> T): NetResult<T>
}