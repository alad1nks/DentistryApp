package com.example.dentistryapp.data.services.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.api.model.CreateAppointmentBody
import com.example.dentistryapp.data.api.model.ServiceResponse
import com.example.dentistryapp.data.api.model.TimeSlotResponse
import com.example.dentistryapp.data.api.model.TimeSlotsBody
import com.example.dentistryapp.domain.model.ServiceDomain
import com.example.dentistryapp.domain.model.TimeSlotDomain
import com.example.dentistryapp.domain.services.repository.ServicesRepository
import com.example.dentistryapp.preferences.SharedPreferences
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(
    private val api: DentistryApi,
    private val preferences: SharedPreferences
) : ServicesRepository {
    override suspend fun getServices(): List<ServiceDomain> {
        return api.getServices().result.toDomain()
    }

    override suspend fun getTimeSlots(
        doctorId: Int,
        serviceId: Int,
        time: String
    ): List<TimeSlotDomain> {
        return api.getTimeSlots(
            TimeSlotsBody(
                doctorId,
                serviceId,
                time
            )
        ).freeTimeslots.toDomainSlot()
    }

    override suspend fun createAppointment(doctorId: Int, serviceId: Int, time: String) {
        api.createAppointment(createAppointmentBody = CreateAppointmentBody(
            doctorId,
            serviceId,
            time
        ), jwt = "jwt=${preferences.getJwt()}")
    }

    private fun List<ServiceResponse>.toDomain(): List<ServiceDomain> {
        return this.map {
            ServiceDomain(
                id = it.id,
                name = it.name,
                description = it.description,
                cost = it.cost,
                duration = it.duration
            )
        }
    }

    private fun List<TimeSlotResponse>.toDomainSlot(): List<TimeSlotDomain> {
        return this.map {
            TimeSlotDomain(
                timeBegin = it.timeBegin,
                timeEnd = it.timeEnd
            )
        }
    }
}