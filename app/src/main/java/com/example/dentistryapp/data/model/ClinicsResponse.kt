package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClinicsResponse(
    @SerialName("clinicList")
    val result: List<ClinicResponse>
)
