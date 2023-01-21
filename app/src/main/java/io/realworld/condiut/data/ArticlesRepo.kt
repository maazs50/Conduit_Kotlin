package io.realworld.condiut.data

import io.realworld.api.ConduitClient

object ArticlesRepo {
    val api = ConduitClient.publicApi
    suspend fun updateGoblalFeed() = api.getArticles()
}