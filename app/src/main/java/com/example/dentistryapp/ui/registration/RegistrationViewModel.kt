package com.example.dentistryapp.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.registration.usecases.RegisterUseCase
import com.example.dentistryapp.preferences.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val preferences: SharedPreferences
) : ViewModel() {
    private val _phoneNumberError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _nameError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _surnameError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _passwordError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _repeatPasswordError: MutableLiveData<Boolean> = MutableLiveData(false)
    val phoneNumberError: LiveData<Boolean> get() = _phoneNumberError
    val nameError: LiveData<Boolean> get() = _nameError
    val surnameError: LiveData<Boolean> get() = _surnameError
    val passwordError: LiveData<Boolean> get() = _passwordError
    val repeatPasswordError: LiveData<Boolean> get() = _repeatPasswordError


    private val _registrationScreenState: MutableLiveData<RegistrationScreenState> = MutableLiveData(RegistrationScreenState.OnRegistration(
        phoneNumberError = false,
        nameError = false,
        surnameError = false,
        passwordError = false,
        repeatPasswordError = false
    ))
    val registrationScreenState: LiveData<RegistrationScreenState> get() = _registrationScreenState

    fun register(phoneNumber: String, name: String, surname: String, password: String, repeatPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val state = registerUseCase.register(phoneNumber, name, surname, password, repeatPassword)
            if (state == RegistrationScreenState.Registered) {
                preferences.setPhoneNumber(phoneNumber)
                preferences.setName(name)
                preferences.setSurname(surname)
            } else if (state is RegistrationScreenState.OnRegistration) {
                _phoneNumberError.postValue(state.phoneNumberError)
                _nameError.postValue(state.nameError)
                _surnameError.postValue(state.surnameError)
                _passwordError.postValue(state.passwordError)
                _repeatPasswordError.postValue(state.repeatPasswordError)
            }
            _registrationScreenState.postValue(state)
        }
    }

    fun removePhoneNumberError() {
        _phoneNumberError.postValue(false)
    }

    fun removeNameError() {
        _nameError.postValue(false)
    }

    fun removeSurnameError() {
        _surnameError.postValue(false)
    }

    fun removePasswordError() {
        _passwordError.postValue(false)
    }

    fun removeRepeatPasswordError() {
        _repeatPasswordError.postValue(false)
    }

    fun registered() {
        _registrationScreenState.value = RegistrationScreenState.OnRegistration(
            phoneNumberError = false,
            nameError = false,
            surnameError = false,
            passwordError = false,
            repeatPasswordError = false
        )
    }
}