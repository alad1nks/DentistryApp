package com.example.dentistryapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentsResponse(
    @SerialName("appointmentList")
    val appointmentList: List<AppointmentResponse>
)
