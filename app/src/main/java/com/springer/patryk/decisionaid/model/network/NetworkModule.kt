package com.springer.patryk.decisionaid.model.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.springer.patryk.decisionaid.model.network.endpoints.GroupsWS
import com.springer.patryk.decisionaid.model.network.endpoints.QuestionWS
import com.springer.patryk.decisionaid.model.network.endpoints.UserWS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Patryk Springer on 2019-01-11.
 */
val networkModule = Kodein.Module(name = "network") {
	bind<OkHttpClient>() with singleton {
		OkHttpClient.Builder().addInterceptor(
			HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
		).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
			.writeTimeout(10, TimeUnit.SECONDS).build()
	}
	bind<Retrofit>() with singleton {
		Retrofit.Builder().baseUrl("http://10.0.2.2:1732")
			.addCallAdapterFactory(CoroutineCallAdapterFactory())
			.addConverterFactory(GsonConverterFactory.create()).client(instance()).build()
	}

	bind<UserWS>() with provider {
		val retrofit: Retrofit = instance()
		retrofit.create(UserWS::class.java)
	}

	bind<GroupsWS>() with provider {
		val retrofit: Retrofit = instance()
		retrofit.create(GroupsWS::class.java)
	}

	bind<QuestionWS>() with provider {
		val retrofit: Retrofit = instance()
		retrofit.create(QuestionWS::class.java)
	}
}