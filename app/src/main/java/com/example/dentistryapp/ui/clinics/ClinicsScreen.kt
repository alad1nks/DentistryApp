package com.example.dentistryapp.ui.clinics

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dentistryapp.ui.SearchBar
import com.example.dentistryapp.ui.model.ClinicItemUi
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ClinicsScreen(
    viewModel: ClinicsViewModel = hiltViewModel()
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
                placeholder = { Text("Клиника") }
            )
        }
        when (state) {
            is ClinicsScreenState.Data -> {
                (state as ClinicsScreenState.Data).items.forEach {
                    item {
                        ClinicItem(item = it)
                    }
                }
            }
            else -> {}
        }
    }
}


@Composable
private fun ClinicItem(
    item: ClinicItemUi,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp)
    ) {
        Text(
            text = item.name,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.address,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.phone,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}