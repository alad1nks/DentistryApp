package com.example.dentistryapp.domain.services.usecases

interface CreateAppointmentUseCase {
    suspend fun createAppointment(doctorId: Int, serviceId: Int, time: String)
}