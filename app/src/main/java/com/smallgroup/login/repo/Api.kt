package com.smallgroup.login.repo


import com.smallgroup.login.domain.model.Auth
import com.smallgroup.login.domain.model.Start
import com.smallgroup.login.domain.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("/mobile/login/")
    fun login(

    ): Deferred<Response<Auth>>

    @GET("/mobile/start/")
    fun start(

    ): Deferred<Response<Start>>

    @POST("/mobile/registration/")
    fun signUp(
        @Body user: User
    ):Deferred<Response<String>>

}