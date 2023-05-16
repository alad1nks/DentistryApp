package com.example.dentistryapp.di

import com.example.dentistryapp.data.appointments.repository.AppointmentsRepositoryImpl
import com.example.dentistryapp.data.clinics.repository.ClinicsRepositoryImpl
import com.example.dentistryapp.data.doctors.repository.DoctorsRepositoryImpl
import com.example.dentistryapp.data.doctors.repository.ReviewsRepositoryImpl
import com.example.dentistryapp.data.login.repository.LoginRepositoryImpl
import com.example.dentistryapp.data.registration.repository.RegistrationRepositoryImpl
import com.example.dentistryapp.data.services.repository.ServicesRepositoryImpl
import com.example.dentistryapp.domain.appointments.repository.AppointmentsRepository
import com.example.dentistryapp.domain.appointments.usecases.GetAppointmentsUseCase
import com.example.dentistryapp.domain.appointments.usecases.GetAppointmentsUseCaseImpl
import com.example.dentistryapp.domain.clinics.repository.ClinicsRepository
import com.example.dentistryapp.domain.clinics.usecases.GetSearchClinicsUseCase
import com.example.dentistryapp.domain.clinics.usecases.GetSearchClinicsUseCaseImpl
import com.example.dentistryapp.domain.doctors.repository.DoctorsRepository
import com.example.dentistryapp.domain.doctors.repository.ReviewsRepository
import com.example.dentistryapp.domain.doctors.usecases.CreateReviewUseCase
import com.example.dentistryapp.domain.doctors.usecases.CreateReviewUseCaseImpl
import com.example.dentistryapp.domain.doctors.usecases.GetReviewsUseCase
import com.example.dentistryapp.domain.doctors.usecases.GetReviewsUseCaseImpl
import com.example.dentistryapp.domain.login.repository.LoginRepository
import com.example.dentistryapp.domain.login.usecases.LoginUseCase
import com.example.dentistryapp.domain.login.usecases.LoginUseCaseImpl
import com.example.dentistryapp.domain.registration.repository.RegistrationRepository
import com.example.dentistryapp.domain.services.repository.ServicesRepository
import com.example.dentistryapp.domain.services.usecases.CreateAppointmentUseCase
import com.example.dentistryapp.domain.services.usecases.CreateAppointmentUseCaseImpl
import com.example.dentistryapp.domain.services.usecases.GetServicesUseCase
import com.example.dentistryapp.domain.services.usecases.GetServicesUseCaseImpl
import com.example.dentistryapp.domain.services.usecases.GetTimeSlotsUseCase
import com.example.dentistryapp.domain.services.usecases.GetTimeSlotsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModuleBinds {
    @Singleton
    @Binds
    abstract fun bindLoginUseCase(useCase: LoginUseCaseImpl) : LoginUseCase

    @Singleton
    @Binds
    abstract fun bindLoginRepository(repo: LoginRepositoryImpl) : LoginRepository

    @Singleton
    @Binds
    abstract fun bindRegistrationRepository(repo: RegistrationRepositoryImpl) : RegistrationRepository

    @Singleton
    @Binds
    abstract fun bindDoctorsRepository(repo: DoctorsRepositoryImpl) : DoctorsRepository

    @Singleton
    @Binds
    abstract fun bindGetSearchClinicsUseCase(useCase: GetSearchClinicsUseCaseImpl) : GetSearchClinicsUseCase

    @Singleton
    @Binds
    abstract fun bindClinicsRepository(repo: ClinicsRepositoryImpl) : ClinicsRepository

    @Singleton
    @Binds
    abstract fun bindGetServicesUseCase(useCase: GetServicesUseCaseImpl) : GetServicesUseCase

    @Singleton
    @Binds
    abstract fun bindCreateAppointmentUseCase(useCase: CreateAppointmentUseCaseImpl) : CreateAppointmentUseCase

    @Singleton
    @Binds
    abstract fun bindServicesRepository(repo: ServicesRepositoryImpl) : ServicesRepository

    @Singleton
    @Binds
    abstract fun bindGetTimeSlotsUseCase(useCase: GetTimeSlotsUseCaseImpl): GetTimeSlotsUseCase

    @Singleton
    @Binds
    abstract fun bindAppointmentsRepository(repo: AppointmentsRepositoryImpl): AppointmentsRepository

    @Singleton
    @Binds
    abstract fun bindGetAppointmentsUseCase(useCase: GetAppointmentsUseCaseImpl): GetAppointmentsUseCase

    @Singleton
    @Binds
    abstract fun bindGetReviewsUseCase(useCase: GetReviewsUseCaseImpl): GetReviewsUseCase

    @Singleton
    @Binds
    abstract fun bindReviewsRepository(repo: ReviewsRepositoryImpl): ReviewsRepository

    @Singleton
    @Binds
    abstract fun bindCreateReviewUseCase(useCase: CreateReviewUseCaseImpl): CreateReviewUseCase
}