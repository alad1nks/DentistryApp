package com.example.dentistryapp.domain.appointments.usecases

import com.example.dentistryapp.domain.model.AppointmentDomain

interface GetAppointmentsUseCase {
    suspend fun getAppointments(): List<AppointmentDomain>
}