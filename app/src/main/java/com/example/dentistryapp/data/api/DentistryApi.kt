package com.example.dentistryapp.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DentistryApi {
    @POST("/user/create")
    suspend fun register(
        @Body registrationBody: com.example.dentistryapp.data.api.model.RegistrationBody
    ): com.example.dentistryapp.data.api.model.ErrorResponse

    @POST("/user/login")
    suspend fun login(
        @Body loginBody: com.example.dentistryapp.data.api.model.LoginBody
    ): com.example.dentistryapp.data.api.model.LoginResponse

    @POST("/doctor/find/namesubstr")
    suspend fun searchDoctors(
        @Body searchDoctorBody: com.example.dentistryapp.data.api.model.SearchDoctorBody
    ): com.example.dentistryapp.data.api.model.GetDoctorsResponse

    @POST("/doctor/get")
    suspend fun getDoctor(
        @Body doctorBody: com.example.dentistryapp.data.api.model.GetDoctorBody
    ): com.example.dentistryapp.data.api.model.GetSelectedDoctorResponse

    @POST("/review/find/doctor")
    suspend fun getReviews(
        @Body getReviewsBody: com.example.dentistryapp.data.api.model.GetReviewsBody
    ): com.example.dentistryapp.data.api.model.GetReviewsResponse

    @POST("/review/create")
    suspend fun createReview(
        @Body createReviewBody: com.example.dentistryapp.data.api.model.CreateReviewBody
    ): com.example.dentistryapp.data.api.model.ErrorResponse

    @POST("/clinic/list")
    suspend fun searchClinics(): com.example.dentistryapp.data.api.model.ClinicsResponse

    @GET("/service/list")
    suspend fun getServices(): com.example.dentistryapp.data.api.model.ServicesResponse

    @POST("/appointment/create/patient")
    suspend fun createAppointment(
        @Body createAppointmentBody: com.example.dentistryapp.data.api.model.CreateAppointmentBody,
        @Header("Cookie") jwt: String
    ): com.example.dentistryapp.data.api.model.ErrorResponse

    @POST("/appointment/free")
    suspend fun getTimeSlots(
        @Body getTimeSlotsBody: com.example.dentistryapp.data.api.model.TimeSlotsBody
    ): com.example.dentistryapp.data.api.model.TimeSlotsResponse

    @POST("/appointment/list/patient")
    suspend fun getAppointments(
        @Body getAppointmentsBody: com.example.dentistryapp.data.api.model.AppointmentsBody
    ): com.example.dentistryapp.data.api.model.AppointmentsResponse

    @GET("/user/whoami")
    suspend fun getProfileInfo(
        @Header("Cookie") jwt: String
    ): com.example.dentistryapp.data.api.model.ProfileInfoResponse

    companion object {
        private const val BASE_URL = "http://84.201.154.197:8083/"
        private val json = Json {
            ignoreUnknownKeys = true
        }

        @OptIn(ExperimentalSerializationApi::class)
        fun create(): DentistryApi {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(DentistryApi::class.java)
        }
    }
}