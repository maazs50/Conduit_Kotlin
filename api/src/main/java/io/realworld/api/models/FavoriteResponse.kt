package io.realworld.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoriteResponse(
    @Json(name = "article")
    val article: Article
)