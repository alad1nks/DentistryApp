package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentsBody(
    @SerialName("pid")
    val pid: Int
)
