package io.realworld.api

import io.realworld.api.Utils.Constants
import io.realworld.api.services.ConduitApi
import io.realworld.api.services.ConduitApiAuth
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ConduitClient {

    var authToken: String? = null
    private val authInterceptor = Interceptor{chain ->
    var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization","Token $it")
                .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())


    val publicApi = retrofitBuilder
        .client(okHttpBuilder.build())
        .build()
        .create(ConduitApi::class.java)

    val authApi = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())
        .build()
        .create(ConduitApiAuth::class.java)
}