package com.example.dentistryapp.domain.services.repository

import com.example.dentistryapp.domain.model.ServiceDomain
import com.example.dentistryapp.domain.model.TimeSlotDomain

interface ServicesRepository {
    suspend fun getServices(): List<ServiceDomain>
    suspend fun getTimeSlots(doctorId: Int, serviceId: Int, time: String): List<TimeSlotDomain>
    suspend fun createAppointment(doctorId: Int, serviceId: Int, time: String)
}