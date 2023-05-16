package com.example.dentistryapp.data.profile

import com.example.dentistryapp.data.api.DentistryApi
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: DentistryApi
) {
    suspend fun getProfileInfo() {
        return
    }
}