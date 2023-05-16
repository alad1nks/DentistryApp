package com.example.dentistryapp.ui.appointments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dentistryapp.ui.greeting.GreetingScreen
import com.example.dentistryapp.ui.model.AppointmentUi

@Composable
fun AppointmentsScreen(
    navController: NavController,
    viewModel: AppointmentsViewModel = hiltViewModel()
) {
    if (!viewModel.isRegistered()) {
        GreetingScreen(navController = navController)
    } else {
        viewModel.getAppointments()
        val items by viewModel.appointments.observeAsState(emptyList())
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
        ) {
            items.forEach {
                item {
                    AppointmentItem(
                        item = it,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppointmentItem(
    item: AppointmentUi,
    viewModel: AppointmentsViewModel
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
        }
    ) {
        Text(
            text = item.date,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.timeBegin,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.timeEnd,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}