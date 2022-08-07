package io.realworld.api.services

import io.realworld.api.models.ArticlesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ConduitApi {
    @GET("articles")
    fun articles(): Call<ArticlesResponse>
}