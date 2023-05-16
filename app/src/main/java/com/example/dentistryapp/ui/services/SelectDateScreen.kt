package com.example.dentistryapp.ui.services

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem

@SuppressLint("SimpleDateFormat")
@ExperimentalMaterial3Api
@Composable
fun SelectDateScreen(
    navController: NavController,
    viewModel: ServicesViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        val datePickerState = rememberDatePickerState()
        DatePicker(
            state = datePickerState,
            title = {
                Text(text = "Выберите дату записи")
            },
            headline = {
                Text(text = "Выберите дату")
            }
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
                .padding(24.dp),
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