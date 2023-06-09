package com.example.dentistryapp.ui.services

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MedicalServices
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem
import com.example.dentistryapp.ui.SearchBar
import com.example.dentistryapp.ui.doctors.DoctorsScreenState
import com.example.dentistryapp.ui.model.DoctorItemUi
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectDoctorScreen(
    navController: NavController,
    viewModel: ServicesViewModel
) {
    val composableScope = rememberCoroutineScope()
    val state by viewModel.searchState.observeAsState()
    var search by rememberSaveable { mutableStateOf("") }
    composableScope.launch {
        viewModel.subscribeToSearchQueryChanges()
        viewModel.searchQueryState.emit("")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Доступные врачи",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.MedicalServices,
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
            item {
                SearchBar(
                    value = search,
                    onValueChange = {
                        search = it
                        composableScope.launch {
                            viewModel.searchQueryState.emit(it)
                        }
                    },
                    placeholder = { Text("Врач") }
                )
            }
            when (state) {
                is DoctorsScreenState.Data -> {
                    (state as DoctorsScreenState.Data).items.forEach {
                        item {
                            DoctorsItem(
                                item = it,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
                else -> {}
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DoctorsItem(
    item: DoctorItemUi,
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
            viewModel.selectDoctor(item.uid)
            navController.navigate(DentistryNavigationItem.SelectDateScreen.screenRoute)
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
            text = item.specialization,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.experience,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}