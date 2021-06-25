package com.app.bygclite.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponses(
    @SerialName("userEmail")
    val userEmail: String?,
    @SerialName("msg")
    val msg: String?,
    @SerialName("status")
    val status: Boolean?
)