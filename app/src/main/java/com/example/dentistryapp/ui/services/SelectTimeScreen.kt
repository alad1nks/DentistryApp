package com.example.dentistryapp.ui.services

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Schedule
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem
import com.example.dentistryapp.ui.model.TimeSlotItemUi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectTimeScreen(
    navController: NavController,
    viewModel: ServicesViewModel
) {
    val composableScope = rememberCoroutineScope()
    val timeSlots by viewModel.timeSlots.observeAsState(emptyList())
    composableScope.launch {
        viewModel.getTimeSlots()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Выберите удобное время",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Schedule,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            timeSlots.forEach {
                item {
                    TimeSlotsItem(item = it, navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimeSlotsItem(
    item: TimeSlotItemUi,
    navController: NavController,
    viewModel: ServicesViewModel
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            viewModel.createAppointment(time = item.time)
            navController.navigate(DentistryNavigationItem.BottomNavigationScreen.screenRoute) {
                popUpTo(0)
            }
        }
    ) {
        Text(
            text = item.timeBegin,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.timeEnd,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}