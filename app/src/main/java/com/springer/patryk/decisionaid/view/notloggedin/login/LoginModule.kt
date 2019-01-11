package com.springer.patryk.decisionaid.view.notloggedin.login

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * Created by Patryk Springer on 2019-01-11.
 */
val loginModule = Kodein.Module(name = "login") {
	bind<LoginContract.View>() with provider { LoginFragment() }
	bind<LoginContract.Presenter>() with provider { LoginPresenter(instance()) }
}