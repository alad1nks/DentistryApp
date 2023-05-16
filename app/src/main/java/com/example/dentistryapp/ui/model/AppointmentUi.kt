package com.example.dentistryapp.ui.model

data class AppointmentUi(
    val id: Int,
    val doctorId: Int,
    val timeBegin: String,
    val timeEnd: String,
    val date: String
)
