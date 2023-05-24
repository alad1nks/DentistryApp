package com.example.dentistryapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAppointmentBody(
    @SerialName("did")
    val did: Int,

    @SerialName("sid")
    val sid: Int,

    @SerialName("time")
    val time: String
)
