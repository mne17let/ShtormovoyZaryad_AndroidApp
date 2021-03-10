package com.smallgroup.login.domain.model

import com.google.gson.annotations.SerializedName

data class Start (
        @SerializedName("a")
        var a: String?,
        @SerializedName("1")
        var b: Int?,
        @SerializedName("2")
        var c: M?
)
data class M(
        @SerializedName("r")
        var r: Int?,
        @SerializedName("p")
        var p: String?
)
