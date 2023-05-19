package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSelectedDoctorInfoResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("uid")
    val uid: Int,

    @SerialName("name")
    val name: String,

    @SerialName("post")
    val post: String,

    @SerialName("exp")
    val exp: Int,

    @SerialName("photo")
    val photo: String,

//    @SerialName("description")
//    val description: String
)
