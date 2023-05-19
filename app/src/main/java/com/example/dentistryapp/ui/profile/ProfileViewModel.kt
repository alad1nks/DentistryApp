package com.example.dentistryapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentistryapp.preferences.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData(sharedPreferences.getName())
    val name: LiveData<String> get() = _name
    private val _surname: MutableLiveData<String> = MutableLiveData(sharedPreferences.getSurname())
    val surname: LiveData<String> get() = _surname
    private val _phoneNumber: MutableLiveData<String> = MutableLiveData(sharedPreferences.getPhoneNumber())
    val phoneNumber: LiveData<String> get() = _phoneNumber
    private val _jwt: MutableLiveData<String> = MutableLiveData(sharedPreferences.getJwt())
    val jwt: LiveData<String> get() = _jwt

    private val _darkMode: MutableLiveData<Boolean> = MutableLiveData(sharedPreferences.isDarkMode())
    val darkMode: LiveData<Boolean> get() = _darkMode

    fun getInfo() {
        _name.postValue(sharedPreferences.getName())
        _surname.postValue(sharedPreferences.getSurname())
        _phoneNumber.postValue(sharedPreferences.getPhoneNumber())
    }

    fun changeDarkMode() {
        _darkMode.value = !_darkMode.value!!
        sharedPreferences.setDarkMode(_darkMode.value!!)
    }

    fun isRegistered(): Boolean = sharedPreferences.isLoggedIn()
    fun logOut() {
        sharedPreferences.logOut()
    }
}