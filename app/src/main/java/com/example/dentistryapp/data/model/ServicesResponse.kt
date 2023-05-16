package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServicesResponse(
    @SerialName("servicelist")
    val result: List<ServiceResponse>
)
