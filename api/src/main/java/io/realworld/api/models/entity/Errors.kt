package io.realworld.api.models.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Errors(
    @Json(name = "body")
    val body: List<String>
)