package com.example.dentistryapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeSlotResponse(
    @SerialName("timebegin")
    val timeBegin: String,

    @SerialName("timeend")
    val timeEnd: String
)
