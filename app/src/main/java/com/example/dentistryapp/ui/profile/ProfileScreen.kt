package com.example.dentistryapp.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dentistryapp.ui.greeting.GreetingScreen

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    if (!viewModel.isRegistered()) {
        GreetingScreen(navController = navController)
    } else {
        val name by viewModel.name.observeAsState()
        val surname by viewModel.surname.observeAsState()
        val phoneNumber by viewModel.phoneNumber.observeAsState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Имя:",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = name!!,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text(
                text = "Фамилия:",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = surname!!,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text(
                text = "Номер телефона:",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = phoneNumber!!,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
//            Text(
//                text = jwt!!,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//                textAlign = TextAlign.Center,
//                fontSize = 20.sp
//            )
        }
    }
}