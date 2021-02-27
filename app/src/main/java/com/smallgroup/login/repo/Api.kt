package com.smallgroup.login.repo


import com.smallgroup.login.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("/mobile/login/")
    suspend fun login(

    ): ResponseWrapper<Unit>

    @POST("/mobile/registration/")
    suspend fun signUp(
        @Body user: User
    ): ResponseWrapper<String>

}