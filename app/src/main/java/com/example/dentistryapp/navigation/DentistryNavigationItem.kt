package com.example.dentistryapp.navigation

sealed class DentistryNavigationItem(var screenRoute: String) {
    object GreetingScreen : DentistryNavigationItem("greeting")
    object RegistrationScreen : DentistryNavigationItem("registration")
    object LoginScreen : DentistryNavigationItem("login")
    object BottomNavigationScreen : DentistryNavigationItem("bottom")
    object ServicesScreen : DentistryNavigationItem("services")
    object SelectDoctorScreen : DentistryNavigationItem("select_doctor")
    object SelectDateScreen : DentistryNavigationItem("select_date")
    object SelectTimeScreen : DentistryNavigationItem("select_time")
    object DoctorReviewsScreen : DentistryNavigationItem("doctor_reviews")
}