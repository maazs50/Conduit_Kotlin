package io.realworld.condiut.data

import io.realworld.api.ConduitClient
import io.realworld.condiut.data.ArticlesRepo.api

object ArticlesRepo {
    val api = ConduitClient().api
    suspend fun updateGoblalFeed() = api.getArticles()
}