package com.example.dentistryapp.ui.doctors

import com.example.dentistryapp.ui.model.DoctorItemUi

sealed interface DoctorsScreenState {
    object Error : DoctorsScreenState

    object Loading : DoctorsScreenState

    class Data(val items: List<DoctorItemUi>) : DoctorsScreenState

    object Init : DoctorsScreenState
}