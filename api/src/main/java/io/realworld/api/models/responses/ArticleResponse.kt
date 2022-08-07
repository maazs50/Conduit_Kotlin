package io.realworld.api.models.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realworld.api.models.entity.Article

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "article")
    val article: Article
)