package com.smallgroup.login.repo

import androidx.lifecycle.MutableLiveData
import com.smallgroup.login.domain.model.Auth
import com.smallgroup.login.domain.model.Start
import com.smallgroup.login.domain.model.User

class SimpleRepo: BaseRepository() {

    var api: Api = NetworkService.retrofitService()

    suspend fun login(): MutableLiveData<Auth?> {
        val response = safeApiCall(
            call = { api.login().await() },
            errorMessage = "Login error"
        )
        return MutableLiveData(response)
    }

    suspend fun start(): MutableLiveData<Start?> {
        val response = safeApiCall(
                call = { api.start().await() },
                errorMessage = "Start error"
        )
        return MutableLiveData(response)
    }

    suspend fun signUp(user: User): MutableLiveData<String?> {
        val response = safeApiCall(
                call = { api.signUp(user).await() },
                errorMessage = "Sign up error"
        )
        return MutableLiveData(response)
    }

}