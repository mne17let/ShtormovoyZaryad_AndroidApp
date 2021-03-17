package com.smallgroup.login.domain.model

import com.google.gson.annotations.SerializedName

data class Auth(
        @SerializedName("auth_token")
        var token: String?
)