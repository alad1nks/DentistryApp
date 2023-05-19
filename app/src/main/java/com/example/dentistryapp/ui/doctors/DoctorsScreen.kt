package com.example.dentistryapp.ui.doctors

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem
import com.example.dentistryapp.ui.SearchBar
import com.example.dentistryapp.ui.model.DoctorItemUi
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DoctorsScreen(
    navController: NavController,
    viewModel: DoctorsViewModel
) {
    val composableScope = rememberCoroutineScope()
    val state by viewModel.searchState.observeAsState()
    var search by rememberSaveable { mutableStateOf("") }
    composableScope.launch {
        viewModel.searchQueryState.emit("")
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(24.dp),
        modifier = Modifier
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DoctorsItem(
    item: DoctorItemUi,
    navController: NavController,
    viewModel: DoctorsViewModel
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            viewModel.selectDoctorId(item.id)
            viewModel.selectDoctorUid(item.uid)
            navController.navigate(DentistryNavigationItem.DoctorInfoScreen.screenRoute)
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
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}