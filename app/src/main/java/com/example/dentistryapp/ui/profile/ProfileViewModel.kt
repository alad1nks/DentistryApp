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

    fun isRegistered(): Boolean = sharedPreferences.isLoggedIn()
}