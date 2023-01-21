package io.realworld.condiut.data

import io.realworld.api.ConduitClient
import io.realworld.api.models.entity.UserCredLogin
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.responses.UserResponse

object UserRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi
    suspend fun login(email:String, password:String): UserResponse? {
        val response = api.loginUser(LoginRequest(UserCredLogin(email,password)))
        ConduitClient.authToken = response.body()!!.user.token
        return response.body()
    }

    suspend fun getUserProfile()= authApi.getCurrentUser().body()

}