package io.realworld.condiut.data

import io.realworld.api.ConduitClient
import io.realworld.api.models.entity.UserCredLogin
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.responses.UserResponse

object UserRepo {
    val api = ConduitClient().api
    suspend fun login(email:String, password:String): UserResponse? {
        val response = api.loginUser(LoginRequest(UserCredLogin(email,password)))
        return response.body()
    }
}