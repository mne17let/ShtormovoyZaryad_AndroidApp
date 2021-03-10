package com.smallgroup.login.domain.model

import com.google.gson.annotations.SerializedName

data class Auth(
        @SerializedName("token")
        var token: String?,
        @SerializedName("secret")
        var secret: String?
)