package com.example.dentistryapp.domain.appointments.usecases

import com.example.dentistryapp.domain.appointments.repository.AppointmentsRepository
import com.example.dentistryapp.domain.model.AppointmentDomain
import javax.inject.Inject

class GetAppointmentsUseCaseImpl @Inject constructor(
    private val repository: AppointmentsRepository
) : GetAppointmentsUseCase {
    override suspend fun getAppointments(): List<AppointmentDomain> {
        return repository.getAppointments()
    }

}