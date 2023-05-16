package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.model.ServiceDomain

interface GetServicesUseCase {
    suspend fun getServices(): List<ServiceDomain>
}