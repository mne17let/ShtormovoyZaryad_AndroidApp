package com.smallgroup.login.domain.model

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("password")
        var password: String?,
        @SerializedName("again_pass")
        var again_pass: String?,
        @SerializedName("mail")
        var email: String?,
        @SerializedName("username")
        var username: String?
    )