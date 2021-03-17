package com.smallgroup.login.repo


import com.smallgroup.login.domain.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("/auth/token/login/")
    fun login(
        @Body login: LoginBody
    ): Deferred<Response<Auth>>

    @GET("/mobile/start/")
    fun start(

    ): Deferred<Response<Start>>

    @POST("/auth/users/")
    fun signUp(
        @Body user: User
    ):Deferred<Response<AuthUser>>

}