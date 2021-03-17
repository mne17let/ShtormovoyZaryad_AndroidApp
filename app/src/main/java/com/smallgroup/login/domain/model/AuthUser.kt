package com.smallgroup.login.domain.model

import com.google.gson.annotations.SerializedName

data class AuthUser (
        @SerializedName("email")
        var email: String?,
        @SerializedName("username")
        var username: String?,
        @SerializedName("id")
        var id: String?
)