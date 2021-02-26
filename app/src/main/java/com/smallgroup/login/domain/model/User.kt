package com.smallgroup.login.domain.model

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("username")
        var username: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("password")
        var password: String?,
        @SerializedName("token")
        var token: Token?
    ) {
    data class Token(
            @SerializedName("session")
            var sessionId: Long?,
            @SerializedName("token")
            var token: String?
        )
}