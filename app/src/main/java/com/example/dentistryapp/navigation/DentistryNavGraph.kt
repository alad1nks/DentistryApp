package com.example.dentistryapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dentistryapp.ui.*
import com.example.dentistryapp.ui.doctors.DoctorInfoScreen
import com.example.dentistryapp.ui.doctors.DoctorReviewsScreen
import com.example.dentistryapp.ui.doctors.DoctorsViewModel
import com.example.dentistryapp.ui.services.SelectDateScreen
import com.example.dentistryapp.ui.services.SelectTimeScreen
import com.example.dentistryapp.ui.greeting.GreetingScreen
import com.example.dentistryapp.ui.login.LoginScreen
import com.example.dentistryapp.ui.profile.ProfileViewModel
import com.example.dentistryapp.ui.registration.RegistrationScreen
import com.example.dentistryapp.ui.services.SelectDoctorScreen
import com.example.dentistryapp.ui.services.ServicesScreen
import com.example.dentistryapp.ui.services.ServicesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DentistryNavGraph(
    modifier: Modifier = Modifier,
    dentistryNavController: NavHostController = rememberNavController(),
    startDestination: String = DentistryNavigationItem.BottomNavigationScreen.screenRoute,
    profileViewModel: ProfileViewModel,
    servicesViewModel: ServicesViewModel = hiltViewModel(),
    doctorsViewModel: DoctorsViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            NavHost(
                navController = dentistryNavController,
                startDestination = startDestination,
                modifier = modifier
            ) {
                composable(DentistryNavigationItem.GreetingScreen.screenRoute) {
                    GreetingScreen(
                        navController = dentistryNavController
                    )
                }
                composable(DentistryNavigationItem.RegistrationScreen.screenRoute) {
                    RegistrationScreen(
                        navController = dentistryNavController
                    )
                }
                composable(DentistryNavigationItem.LoginScreen.screenRoute) {
                    LoginScreen(
                        navController = dentistryNavController
                    )
                }
                composable(DentistryNavigationItem.BottomNavigationScreen.screenRoute) {
                    BottomNavGraph(
                        dentistryNavController = dentistryNavController,
                        doctorsViewModel = doctorsViewModel,
                        profileViewModel = profileViewModel
                    )
                }
                composable(DentistryNavigationItem.ServicesScreen.screenRoute) {
                    ServicesScreen(
                        navController = dentistryNavController,
                        viewModel = servicesViewModel
                    )
                }
                composable(DentistryNavigationItem.SelectDoctorScreen.screenRoute) {
                    SelectDoctorScreen(
                        navController = dentistryNavController,
                        viewModel = servicesViewModel
                    )
                }
                composable(DentistryNavigationItem.SelectDateScreen.screenRoute) {
                    SelectDateScreen(
                        navController = dentistryNavController,
                        viewModel = servicesViewModel
                    )
                }
                composable(DentistryNavigationItem.SelectTimeScreen.screenRoute) {
                    SelectTimeScreen(
                        navController = dentistryNavController,
                        viewModel = servicesViewModel
                    )
                }
                composable(DentistryNavigationItem.DoctorInfoScreen.screenRoute) {
                    DoctorInfoScreen(
                        navController = dentistryNavController,
                        viewModel = doctorsViewModel
                    )
                }
                composable(DentistryNavigationItem.DoctorReviewsScreen.screenRoute) {
                    DoctorReviewsScreen(
                        navController = dentistryNavController,
                        viewModel = doctorsViewModel
                    )
                }
            }
        }
    )
}