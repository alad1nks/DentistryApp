package com.example.dentistryapp.domain.clinics.usecases

import com.example.dentistryapp.domain.model.ClinicDomain
import com.example.dentistryapp.ui.model.ClinicItemUi

object ClinicsUiFactory {
    fun create(searchResult: List<ClinicDomain>): List<ClinicItemUi> {
        return searchResult.map {
            ClinicItemUi(
                name = it.name,
                address = it.address,
                phone = it.phone
            )
        }
    }
}