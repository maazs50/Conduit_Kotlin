package io.realworld.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realworld.api.models.entity.UserUpdate

@JsonClass(generateAdapter = true)
data class UpdateUserRequest(
    @Json(name = "user")
    val user: UserUpdate
)