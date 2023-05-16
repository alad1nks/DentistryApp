package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.services.repository.ServicesRepository
import javax.inject.Inject

class CreateAppointmentUseCaseImpl @Inject constructor(
    private val repository: ServicesRepository
) : CreateAppointmentUseCase {
    override suspend fun createAppointment(doctorId: Int, serviceId: Int, time: String) {
        repository.createAppointment(doctorId, serviceId, time)
    }
}