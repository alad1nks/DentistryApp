package com.example.dentistryapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDoctorResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("uid")
    val uid: Int,

    @SerialName("name")
    val name: String,

    @SerialName("post")
    val post: String,

    @SerialName("exp")
    val exp: Int
)