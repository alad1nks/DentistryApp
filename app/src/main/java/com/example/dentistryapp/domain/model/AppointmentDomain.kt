package com.example.dentistryapp.domain.model

data class AppointmentDomain(
    val id: Int,
    val doctorId: Int,
    val timeBegin: String,
    val timeEnd: String
)
