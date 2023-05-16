package com.example.dentistryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetReviewsResponse(
    @SerialName("reviewList")
    val reviewList: List<GetReviewResponse>,
)
