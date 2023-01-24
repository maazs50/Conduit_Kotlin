package io.realworld.condiut.data

import android.content.SharedPreferences
import io.realworld.api.ConduitClient
import io.realworld.api.models.entity.User
import io.realworld.api.models.entity.UserCred
import io.realworld.api.models.entity.UserCredLogin
import io.realworld.api.models.entity.UserUpdate
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.requests.UpdateUserRequest
import io.realworld.api.models.responses.UserResponse

object UserRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi
    suspend fun login(email:String, password:String): User? {
        val response = api.loginUser(LoginRequest(UserCredLogin(email,password)))
        //TODO:Save it in SharedPreferences
        ConduitClient.authToken = response.body()!!.user.token
        return response.body()?.user
    }

    suspend fun getUserProfile(token:String): User?{
        ConduitClient.authToken = token
        val response = authApi.getCurrentUser().body()?.user
        return response
    }

    suspend fun signup(username: String, email:String, password: String): User? {
        val response = api.signupUser(SignupRequest(UserCred(email,password,username)))
        ConduitClient.authToken = response.body()!!.user.token
        return response.body()?.user
    }

    suspend fun updateSettings(
        email:String?,
        username:String?,
        password:String?,
        image:String?,
        bio:String?
    ): User?{
        val response = authApi.updateCurrentUser(
            UpdateUserRequest(
                UserUpdate(
                    bio = bio,email = email,image = image,password = password,username = username
                )))
        return response.body()?.user
    }
}