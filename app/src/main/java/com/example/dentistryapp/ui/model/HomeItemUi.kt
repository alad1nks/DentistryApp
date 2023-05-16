package com.example.dentistryapp.ui.model

import androidx.navigation.NavController

data class HomeItemUi(
    val name: String,
    val maxLine: Boolean,
    val onClick: (NavController) -> Unit
)