package com.example.dentistryapp.domain.model

data class ServiceDomain(
    val id: Int,
    val name: String,
    val description: String,
    val cost: Int,
    val duration: Int
)