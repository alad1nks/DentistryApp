package com.example.dentistryapp.ui.appointments

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dentistryapp.ui.greeting.GreetingScreen
import com.example.dentistryapp.ui.model.AppointmentUi

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Ваши записи",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Outlined.Medication,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(24.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                items.forEach {
                    item {
                        AppointmentItem(
                            item = it
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppointmentItem(
    item: AppointmentUi
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