package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDoctorsResponse(
    @SerialName("result")
    val result: List<GetDoctorResponse>
)
