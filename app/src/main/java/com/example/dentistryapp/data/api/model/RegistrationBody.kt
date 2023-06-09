package com.example.dentistryapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationBody(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String,
    @SerialName("role")
    val role: Int
)