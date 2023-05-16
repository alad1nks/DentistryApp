package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeSlotsResponse(
    @SerialName("freeTimeslots")
    val freeTimeslots: List<TimeSlotResponse>
)
