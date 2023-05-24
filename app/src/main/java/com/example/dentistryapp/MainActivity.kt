package com.example.dentistryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.dentistryapp.navigation.DentistryNavGraph
import com.example.dentistryapp.ui.profile.ProfileViewModel
import com.example.dentistryapp.ui.theme.DentistryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ProfileViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DentistryTheme(
                viewModel = viewModel
            ) {
                DentistryNavGraph(
                    profileViewModel = viewModel
                )
            }
        }
    }
}
