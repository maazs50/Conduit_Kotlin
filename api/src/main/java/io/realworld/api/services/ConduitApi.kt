package io.realworld.api.services

import io.realworld.api.Utils.Constants
import io.realworld.api.models.entity.User
import io.realworld.api.models.entity.UserCred
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.requests.UpdateUserRequest
import io.realworld.api.models.responses.*
import retrofit2.Response
import retrofit2.http.*
import javax.print.attribute.standard.JobOriginatingUserName

interface ConduitApi {
    @POST("users")
    suspend fun signupUser(
        @Body userCreds: SignupRequest
    ): Response<UserResponse>

    @POST("users")
    suspend fun loginUser(
        @Body userCreds: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query(Constants.AUTHOR) author: String? = null,
        @Query(Constants.FAVORITED) favorited: String? = null,
        @Query(Constants.TAG) tag: String? = null
    ): Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticlesBySlug(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>
}