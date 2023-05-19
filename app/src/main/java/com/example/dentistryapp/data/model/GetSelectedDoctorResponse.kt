package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSelectedDoctorResponse(
    @SerialName("info")
    val info: GetSelectedDoctorInfoResponse
)
