package com.example.dentistryapp.domain.model


data class DoctorDomain(
    val id: Int,
    val uid: Int,
    val name: String,
    val specialization: String,
    val experience: Int
)
