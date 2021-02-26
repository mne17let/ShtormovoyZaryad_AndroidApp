package com.smallgroup.login.repo


import com.smallgroup.login.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("login")
    suspend fun login(

    )

    @POST("registration")
    suspend fun signUp(
        @Query("password") pass: String,
        @Query("again_pass") againPass: String,
        @Query("mail") email: String,
        @Query("username") username: String
    ): ResponseWrapper<String>

}