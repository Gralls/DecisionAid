package com.springer.patryk.decisionaid.model.network

sealed class NetResult<out T : Any> {

	class Success<out T : Any>(val data: T) : NetResult<T>()

	class Error(val exception: Throwable) : NetResult<Nothing>()
}