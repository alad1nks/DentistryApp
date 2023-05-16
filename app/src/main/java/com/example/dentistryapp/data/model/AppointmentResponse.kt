package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("pid")
    val pid: Int,

    @SerialName("did")
    val did: Int,

    @SerialName("sid")
    val sid: Int,

    @SerialName("timebegin")
    val timeBegin: String,

    @SerialName("timeend")
    val timeEnd: String
)
