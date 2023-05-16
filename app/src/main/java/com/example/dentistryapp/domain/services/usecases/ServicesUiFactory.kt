package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.model.ServiceDomain
import com.example.dentistryapp.ui.model.ServiceItemUi

object ServicesUiFactory {
    fun create(searchResult: List<ServiceDomain>): List<ServiceItemUi> {
        return searchResult.map {
            ServiceItemUi(
                id = it.id,
                name = it.name,
                description = it.description,
                cost = it.cost.toString(),
                duration = it.duration.toString()
            )
        }
    }
}