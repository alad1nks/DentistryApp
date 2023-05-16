package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.domain.model.DoctorDomain
import com.example.dentistryapp.ui.model.DoctorItemUi

object DoctorsUiFactory {
    fun create(searchResult: List<DoctorDomain>): List<DoctorItemUi> {
        return searchResult.map {
            val year = when (it.experience % 10) {
                1 -> "год"
                in 2..4 -> "года"
                else -> "лет"
            }
            DoctorItemUi(
                id = it.id,
                uid = it.uid,
                name = it.name,
                specialization = it.specialization,
                experience = "Стаж: ${it.experience} $year"
            )
        }
    }
}