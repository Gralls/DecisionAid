package com.springer.patryk.decisionaid

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class DecisionAidApp : Application(), KodeinAware {
	override val kodein: Kodein
		get() = Kodein.lazy {
			bind<OkHttpClient>() with singleton {
				OkHttpClient.Builder().addInterceptor(
						HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
						.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
						.writeTimeout(10, TimeUnit.SECONDS).build()
			}
			bind<Retrofit>() with singleton {
				Retrofit.Builder().baseUrl("http://150.254.78.162:1732")
						.addCallAdapterFactory(CoroutineCallAdapterFactory())
						.addConverterFactory(GsonConverterFactory.create()).client(instance())
						.build()
			}
		}

	override fun onCreate() {
		super.onCreate()
		Timber.plant(Timber.DebugTree())
	}
}