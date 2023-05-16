package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReviewBody(
    @SerialName("did")
    val did: Int,

    @SerialName("score")
    val score: Int,

    @SerialName("description")
    val description: String,
)
