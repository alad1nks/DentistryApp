package com.example.dentistryapp.ui.appointments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.appointments.usecases.AppointmentsUiFactory
import com.example.dentistryapp.domain.appointments.usecases.GetAppointmentsUseCase
import com.example.dentistryapp.preferences.SharedPreferences
import com.example.dentistryapp.ui.model.AppointmentUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val getAppointmentsUseCase: GetAppointmentsUseCase
) : ViewModel() {
    private val _appointments: MutableLiveData<List<AppointmentUi>> = MutableLiveData()
    val appointments: LiveData<List<AppointmentUi>> get() = _appointments


    fun getAppointments() {
        viewModelScope.launch(Dispatchers.IO) {
            _appointments.postValue(AppointmentsUiFactory.create(getAppointmentsUseCase.getAppointments()))
        }
    }

    fun isRegistered(): Boolean = sharedPreferences.isLoggedIn()
}