package com.example.dentistryapp.di

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.registration.repository.RegistrationRepositoryImpl
import com.example.dentistryapp.domain.registration.repository.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideDentistryApi(): DentistryApi {
        return DentistryApi.create()
    }
}