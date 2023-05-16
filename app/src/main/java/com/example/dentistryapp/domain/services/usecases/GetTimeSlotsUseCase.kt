package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.model.TimeSlotDomain

interface GetTimeSlotsUseCase {
    suspend fun getTimeSlots(doctorId: Int, serviceId: Int, time: String): List<TimeSlotDomain>
}