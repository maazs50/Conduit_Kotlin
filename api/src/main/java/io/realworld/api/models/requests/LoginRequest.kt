package io.realworld.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realworld.api.models.entity.UserCredLogin

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "user")
    val user: UserCredLogin
)