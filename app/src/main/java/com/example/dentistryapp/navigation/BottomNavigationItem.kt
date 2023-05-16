package com.example.dentistryapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(val title: String, val icon: ImageVector, val selectedIcon: ImageVector, val screenRoute: String) {
    object HomeScreen : BottomNavigationItem("Главная", Icons.Outlined.Home, Icons.Filled.Home,"home")
    object AppointmentsScreen: BottomNavigationItem("Приемы", Icons.Outlined.Assignment, Icons.Filled.Assignment,"appointments")
    object DoctorsScreen: BottomNavigationItem("Врачи", Icons.Outlined.ContactEmergency, Icons.Filled.ContactEmergency,"doctors")
    object ClinicsScreen: BottomNavigationItem("Клиники", Icons.Outlined.LocalHospital, Icons.Filled.LocalHospital,"clinics")
    object ProfileScreen: BottomNavigationItem("Профиль", Icons.Outlined.AccountBox, Icons.Filled.AccountBox,"profile")
}
