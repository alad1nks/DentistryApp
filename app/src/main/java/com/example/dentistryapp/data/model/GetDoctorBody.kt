package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDoctorBody(
    @SerialName("uid")
    val uid: Int
)
