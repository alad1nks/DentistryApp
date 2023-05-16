package com.example.dentistryapp.ui.services

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.doctors.usecases.DoctorsUiFactory
import com.example.dentistryapp.domain.doctors.usecases.GetDoctorsUseCaseImpl
import com.example.dentistryapp.domain.services.usecases.CreateAppointmentUseCase
import com.example.dentistryapp.domain.services.usecases.GetServicesUseCase
import com.example.dentistryapp.domain.services.usecases.GetTimeSlotsUseCase
import com.example.dentistryapp.domain.services.usecases.ServicesUiFactory
import com.example.dentistryapp.domain.services.usecases.TimeSlotUiFactory
import com.example.dentistryapp.ktx.runCatchingNonCancellation
import com.example.dentistryapp.preferences.SharedPreferences
import com.example.dentistryapp.ui.doctors.DoctorsScreenState
import com.example.dentistryapp.ui.model.ServiceItemUi
import com.example.dentistryapp.ui.model.TimeSlotItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val createAppointmentUseCase: CreateAppointmentUseCase,
    private val getServicesUseCase: GetServicesUseCase,
    private val getSearchDoctorsUseCase: GetDoctorsUseCaseImpl,
    private val getTimeSlotsUseCase: GetTimeSlotsUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _services: MutableLiveData<List<ServiceItemUi>> = MutableLiveData()
    val services: LiveData<List<ServiceItemUi>> get() = _services

    private val _selectedService: MutableLiveData<Int> = MutableLiveData()
    val selectedService: LiveData<Int> get() = _selectedService

    private val _selectedDoctor: MutableLiveData<Int> = MutableLiveData()
    val selectedDoctor: LiveData<Int> get() = _selectedDoctor

    private val _searchState: MutableLiveData<DoctorsScreenState> =
        MutableLiveData(DoctorsScreenState.Init)
    val searchState: LiveData<DoctorsScreenState> get() = _searchState
    val searchQueryState: MutableSharedFlow<String> = MutableSharedFlow()

    private val _selectedDate: MutableLiveData<String> = MutableLiveData()
    val selectedDate: LiveData<String> get() = _selectedDate

    private val _timeSlots: MutableLiveData<List<TimeSlotItemUi>> = MutableLiveData()
    val timeSlots: LiveData<List<TimeSlotItemUi>> get() = _timeSlots


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun subscribeToSearchQueryChanges() {
        searchQueryState
            .distinctUntilChanged()
            .debounce(500L)
            .flatMapLatest { flow { emit(search(it)) } }
            .onEach { _searchState.postValue(it) }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private suspend fun search(query: String): DoctorsScreenState {
        _searchState.postValue(DoctorsScreenState.Loading)
        val result = runCatchingNonCancellation {
            DoctorsUiFactory.create(
                searchResult = getSearchDoctorsUseCase.search(query),
            )
        }.getOrNull()

        return result?.let { DoctorsScreenState.Data(it) }
            ?: DoctorsScreenState.Error
    }

    fun getServices() {
        viewModelScope.launch(Dispatchers.IO) {
            _services.postValue(ServicesUiFactory.create(getServicesUseCase.getServices()))
        }
    }

    fun selectService(id: Int) {
        _selectedService.postValue(id)
    }

    fun selectDoctor(id: Int) {
        _selectedDoctor.postValue(id)
    }

    fun createAppointment(time: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createAppointmentUseCase.createAppointment(
                selectedDoctor.value!!,
                selectedService.value!!,
                time
            )
        }
    }

    fun selectDate(date: String) {
        _selectedDate.postValue(date)
    }

    fun getTimeSlots() {
        Log.d("launch", selectedDoctor.value!!.toString())
        Log.d("launch", selectedService.value!!.toString())
        Log.d("launch", selectedDate.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            _timeSlots.postValue(
                TimeSlotUiFactory.create(
                    timeSlots = getTimeSlotsUseCase.getTimeSlots(
                        selectedDoctor.value!!,
                        selectedService.value!!,
                        selectedDate.value!!
                    )
                )
            )
        }
    }

    fun isRegistered(): Boolean = sharedPreferences.isLoggedIn()
}