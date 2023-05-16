package com.example.dentistryapp.data.appointments.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.model.AppointmentsBody
import com.example.dentistryapp.data.model.AppointmentsResponse
import com.example.dentistryapp.domain.appointments.repository.AppointmentsRepository
import com.example.dentistryapp.domain.model.AppointmentDomain
import com.example.dentistryapp.preferences.SharedPreferences
import javax.inject.Inject

class AppointmentsRepositoryImpl @Inject constructor(
    private val api: DentistryApi,
    private val preferences: SharedPreferences
) : AppointmentsRepository {
    override suspend fun getAppointments(): List<AppointmentDomain> {
        return api.getAppointments(AppointmentsBody(preferences.getId())).toDomain()
    }

    private fun AppointmentsResponse.toDomain(): List<AppointmentDomain> {
        return this.appointmentList.map {
            AppointmentDomain(
                id = it.id,
                doctorId = it.did,
                timeBegin = it.timeBegin,
                timeEnd = it.timeEnd
            )
        }
    }

}