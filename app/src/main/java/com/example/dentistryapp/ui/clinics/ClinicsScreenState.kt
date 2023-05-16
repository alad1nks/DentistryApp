package com.example.dentistryapp.ui.clinics

import com.example.dentistryapp.ui.doctors.DoctorsScreenState
import com.example.dentistryapp.ui.model.ClinicItemUi
import com.example.dentistryapp.ui.model.DoctorItemUi

sealed interface ClinicsScreenState {
    object Error : ClinicsScreenState

    object Loading : ClinicsScreenState

    class Data(val items: List<ClinicItemUi>) : ClinicsScreenState

    object Init : ClinicsScreenState
}