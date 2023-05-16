package com.example.dentistryapp.domain.appointments.usecases

import com.example.dentistryapp.domain.model.AppointmentDomain
import com.example.dentistryapp.ui.model.AppointmentUi

object AppointmentsUiFactory {
    fun create(result: List<AppointmentDomain>): List<AppointmentUi> {
        return result.map {
            AppointmentUi(
                it.id,
                it.doctorId,
                "Начало: ${it.timeBegin.substring(11, 16)}",
                "Конец: ${it.timeEnd.substring(11, 16)}",
                "Дата: ${it.timeBegin.take(10)}"
            )
        }
    }
}