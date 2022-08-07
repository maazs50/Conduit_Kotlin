package io.realworld.api.services

import io.realworld.api.Utils.Constants
import io.realworld.api.models.entity.User
import io.realworld.api.models.entity.UserCred
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.responses.ArticlesResponse
import io.realworld.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ConduitApi {
    @POST("users")
    suspend fun signupUser(
        @Body user: SignupRequest
    ): Response<UserResponse>
    @GET("articles")
    suspend fun getArticles(
        @Query(Constants.AUTHOR) author: String? = null,
        @Query(Constants.FAVORITED) favorited: String? = null,
        @Query(Constants.TAG) tag: String? = null
    ): Response<ArticlesResponse>
}