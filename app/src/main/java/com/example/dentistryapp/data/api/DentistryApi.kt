package com.example.dentistryapp.data.api

import com.example.dentistryapp.data.model.AppointmentsBody
import com.example.dentistryapp.data.model.AppointmentsResponse
import com.example.dentistryapp.data.model.ClinicsResponse
import com.example.dentistryapp.data.model.CreateAppointmentBody
import com.example.dentistryapp.data.model.CreateReviewBody
import com.example.dentistryapp.data.model.SearchDoctorBody
import com.example.dentistryapp.data.model.GetDoctorsResponse
import com.example.dentistryapp.data.model.ErrorResponse
import com.example.dentistryapp.data.model.GetDoctorBody
import com.example.dentistryapp.data.model.GetReviewsBody
import com.example.dentistryapp.data.model.GetReviewsResponse
import com.example.dentistryapp.data.model.GetSelectedDoctorResponse
import com.example.dentistryapp.data.model.LoginBody
import com.example.dentistryapp.data.model.LoginResponse
import com.example.dentistryapp.data.model.ProfileInfoResponse
import com.example.dentistryapp.data.model.RegistrationBody
import com.example.dentistryapp.data.model.ServicesResponse
import com.example.dentistryapp.data.model.TimeSlotsBody
import com.example.dentistryapp.data.model.TimeSlotsResponse
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
        @Body registrationBody: RegistrationBody
    ): ErrorResponse

    @POST("/user/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse

    @POST("/doctor/find/namesubstr")
    suspend fun searchDoctors(
        @Body searchDoctorBody: SearchDoctorBody
    ): GetDoctorsResponse

    @POST("/doctor/get")
    suspend fun getDoctor(
        @Body doctorBody: GetDoctorBody
    ): GetSelectedDoctorResponse

    @POST("/review/find/doctor")
    suspend fun getReviews(
        @Body getReviewsBody: GetReviewsBody
    ): GetReviewsResponse

    @POST("/review/create")
    suspend fun createReview(
        @Body createReviewBody: CreateReviewBody
    ): ErrorResponse

    @POST("/clinic/list")
    suspend fun searchClinics(): ClinicsResponse

    @GET("/service/list")
    suspend fun getServices(): ServicesResponse

    @POST("/appointment/create/patient")
    suspend fun createAppointment(
        @Body createAppointmentBody: CreateAppointmentBody,
        @Header("Cookie") jwt: String
    ): ErrorResponse

    @POST("/appointment/free")
    suspend fun getTimeSlots(
        @Body getTimeSlotsBody: TimeSlotsBody
    ): TimeSlotsResponse

    @POST("/appointment/list/patient")
    suspend fun getAppointments(
        @Body getAppointmentsBody: AppointmentsBody
    ): AppointmentsResponse

    @GET("/user/whoami")
    suspend fun getProfileInfo(
        @Header("Cookie") jwt: String
    ): ProfileInfoResponse

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