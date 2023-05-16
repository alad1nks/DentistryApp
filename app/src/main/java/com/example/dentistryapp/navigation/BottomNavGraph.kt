package com.example.dentistryapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dentistryapp.ui.appointments.AppointmentsScreen
import com.example.dentistryapp.ui.clinics.ClinicsScreen
import com.example.dentistryapp.ui.doctors.DoctorsScreen
import com.example.dentistryapp.ui.doctors.DoctorsViewModel
import com.example.dentistryapp.ui.home.HomeScreen
import com.example.dentistryapp.ui.profile.ProfileScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    bottomNavController: NavHostController = rememberNavController(),
    startDestination: String = BottomNavigationItem.HomeScreen.screenRoute,
    dentistryNavController: NavController,
    doctorsViewModel: DoctorsViewModel
) {
    val items = listOf(
        BottomNavigationItem.HomeScreen,
        BottomNavigationItem.AppointmentsScreen,
        BottomNavigationItem.DoctorsScreen,
        BottomNavigationItem.ClinicsScreen,
        BottomNavigationItem.ProfileScreen
    )
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = { NavigationBar{
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        if (currentRoute == item.screenRoute) {
                            Icon(item.selectedIcon, contentDescription = item.title)
                        } else {
                            Icon(item.icon, contentDescription = item.title)
                        }
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screenRoute,
                    onClick = {
                        bottomNavController.navigate(item.screenRoute) {
                            bottomNavController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        } },
        content = {
            Box(modifier = Modifier.padding(it)) {
                NavHost(
                    navController = bottomNavController,
                    startDestination = startDestination,
                    modifier = modifier
                ) {
                    composable(BottomNavigationItem.HomeScreen.screenRoute) {
                        HomeScreen(
                            navController = dentistryNavController
                        )
                    }
                    composable(BottomNavigationItem.AppointmentsScreen.screenRoute) {
                        AppointmentsScreen(
                            navController = dentistryNavController
                        )
                    }
                    composable(BottomNavigationItem.DoctorsScreen.screenRoute) {
                        DoctorsScreen(
                            navController = dentistryNavController,
                            viewModel = doctorsViewModel
                        )
                    }
                    composable(BottomNavigationItem.ClinicsScreen.screenRoute) {
                        ClinicsScreen()
                    }
                    composable(BottomNavigationItem.ProfileScreen.screenRoute) {
                        ProfileScreen(
                            navController = dentistryNavController
                        )
                    }
                }
            }
        }
    )
}