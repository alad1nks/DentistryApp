package com.example.dentistryapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Emergency
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dentistryapp.ui.model.HomeItemUi

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val items by viewModel.items.observeAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Клиника стоматологии",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Emergency,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = object : GridCells {
                override fun Density.calculateCrossAxisCellSizes(
                    availableSize: Int,
                    spacing: Int
                ): List<Int> {
                    val firstColumn = (availableSize - spacing) * 2 / 3
                    val secondColumn = availableSize - spacing - firstColumn
                    return listOf(firstColumn, secondColumn)
                }
            },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            items?.forEach {
                item(
                    span = {
                        GridItemSpan(
                            when(it.maxLine) {
                                true -> maxLineSpan
                                else -> 1
                            }
                        )
                    }
                ) {
                    HomeItem(
                        homeItemUi = it,
                        navController = navController
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeItem(
    homeItemUi: HomeItemUi,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            homeItemUi.onClick(navController)
        }
    ) {
        Row(
            modifier = Modifier
        ) {
            Text(
                text = homeItemUi.name,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                fontSize = 24.sp,
                lineHeight = 36.sp
            )
        }
    }
}