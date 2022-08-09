package io.realworld.api

import io.realworld.api.Utils.Constants
import io.realworld.api.services.ConduitApi
import io.realworld.api.services.ConduitApiAuth
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConduitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(ConduitApiAuth::class.java)
}