package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.ui.model.SelectedDoctorUi

interface GetSelectedDoctorUseCase {
    suspend fun getSelectedDoctor(id: Int): SelectedDoctorUi
}