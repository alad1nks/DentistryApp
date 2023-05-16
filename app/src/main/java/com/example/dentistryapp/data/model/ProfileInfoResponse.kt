package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileInfoResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("login")
    val login: String,

    @SerialName("role")
    val role: Int
)
