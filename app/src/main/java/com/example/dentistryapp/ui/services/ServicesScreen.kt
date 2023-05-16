package com.example.dentistryapp.ui.services

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem
import com.example.dentistryapp.ui.greeting.GreetingScreen
import com.example.dentistryapp.ui.model.ServiceItemUi

@Composable
fun ServicesScreen(
    navController: NavController,
    viewModel: ServicesViewModel
) {
    if (!viewModel.isRegistered()) {
        GreetingScreen(navController = navController)
    } else {
        viewModel.getServices()
        val items by viewModel.services.observeAsState(emptyList())
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
        ) {
            items.forEach {
                item {
                    ServiceItem(
                        item = it,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ServiceItem(
    item: ServiceItemUi,
    navController: NavController,
    viewModel: ServicesViewModel
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp),
        onClick = {
            viewModel.selectService(item.id)
            navController.navigate(DentistryNavigationItem.SelectDoctorScreen.screenRoute)
        }
    ) {
        Text(
            text = item.name,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.description,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = "${item.cost}р",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}