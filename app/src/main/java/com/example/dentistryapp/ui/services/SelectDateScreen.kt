package com.example.dentistryapp.ui.services

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem

@SuppressLint("SimpleDateFormat", "UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SelectDateScreen(
    navController: NavController,
    viewModel: ServicesViewModel
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Выберите удобную дату",
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
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            val datePickerState = rememberDatePickerState()
            DatePicker(
                state = datePickerState,
                modifier = Modifier,
                title = {},
                headline = {}
            )

            FilledIconButton(
                onClick = {
                    if (datePickerState.selectedDateMillis != null) {
                        viewModel.selectDate(java.text.SimpleDateFormat("yyyy-MM-dd").format(datePickerState.selectedDateMillis))
                        navController.navigate(DentistryNavigationItem.SelectTimeScreen.screenRoute)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                content = {
                    Text(
                        text = "Выбрать время",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )
                }
            )
        }
    }
}