package com.smallgroup.login.repo

import com.google.gson.annotations.SerializedName

class ResponseWrapper<T> {

    @SerializedName("response")
    val data: T? = null
    @SerializedName("error")
    val error: Error? = null


}