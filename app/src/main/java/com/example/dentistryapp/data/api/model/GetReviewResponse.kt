package com.example.dentistryapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetReviewResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("cid")
    val cid: Int,

    @SerialName("did")
    val did: Int,

    @SerialName("sid")
    val sid: Int,

    @SerialName("score")
    val score: Int,

    @SerialName("description")
    val description: String
)
