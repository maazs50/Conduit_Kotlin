package io.realworld.condiut

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realworld.api.ConduitClient
import io.realworld.api.models.entity.User
import io.realworld.condiut.data.UserRepo
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user :LiveData<User?> = _user
    fun login(email:String, password:String){
        viewModelScope.launch {
            UserRepo.login(email,password)?.let { _user.postValue(it) }
        }
    }

    fun signup(username: String, email: String, password: String){
        viewModelScope.launch {
            UserRepo.signup(username,email,password)?.let { _user.postValue(it) }
        }
    }

    fun getCurrentUser(token: String) {
        viewModelScope.launch {
            UserRepo.getUserProfile(token)?.let { _user.postValue(it) }
        }
    }
    fun updateUser(
        email:String?,
        username:String?,
        password:String?,
        image:String?,
        bio:String?
    ) = viewModelScope.launch {
        UserRepo.updateSettings(email,username,password,image,bio)?.let {
            _user.postValue(it)
        }
    }

    fun logout(){
        _user.postValue(null)
    }
}