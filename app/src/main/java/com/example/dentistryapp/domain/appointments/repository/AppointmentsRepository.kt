package com.example.dentistryapp.domain.appointments.repository

import com.example.dentistryapp.domain.model.AppointmentDomain

interface AppointmentsRepository {
    suspend fun getAppointments(): List<AppointmentDomain>
}