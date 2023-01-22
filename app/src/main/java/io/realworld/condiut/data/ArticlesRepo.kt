package io.realworld.condiut.data

import io.realworld.api.ConduitClient

object ArticlesRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi
    suspend fun updateGoblalFeed() = api.getArticles()
    suspend fun updateMyFeed() = authApi.getFeedArticles()
}