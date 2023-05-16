package com.example.dentistryapp.domain.model

data class ReviewDomain(
    val id: Int,
    val cid: Int,
    val did: Int,
    val sid: Int,
    val score: Int,
    val description: String
)
