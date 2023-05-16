package com.example.dentistryapp.domain.services.usecases

import com.example.dentistryapp.domain.model.ServiceDomain
import com.example.dentistryapp.domain.services.repository.ServicesRepository
import javax.inject.Inject

class GetServicesUseCaseImpl @Inject constructor(
    private val repository: ServicesRepository
) : GetServicesUseCase {
    override suspend fun getServices(): List<ServiceDomain> {
        return repository.getServices()
    }
}