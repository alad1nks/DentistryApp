package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.model.TimeSlotDomain
import com.example.dentistryapp.domain.services.repository.ServicesRepository
import javax.inject.Inject

class GetTimeSlotsUseCaseImpl @Inject constructor(
    private val repository: ServicesRepository
) : GetTimeSlotsUseCase {
    override suspend fun getTimeSlots(doctorId: Int, serviceId: Int, time: String): List<TimeSlotDomain> {
        return repository.getTimeSlots(doctorId, serviceId, time)
    }
}