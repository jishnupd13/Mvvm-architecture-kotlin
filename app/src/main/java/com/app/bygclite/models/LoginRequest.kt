package com.app.bygclite.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("user_name")
    val userName: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("phone_code")
    val phoneCode: Int?,
    @SerialName("phone")
    val phone: Int?,
    @SerialName("password")
    val password: String?,
    @SerialName("user_type")
    val userType: Int?,
    @SerialName("address")
    val address: String?
)