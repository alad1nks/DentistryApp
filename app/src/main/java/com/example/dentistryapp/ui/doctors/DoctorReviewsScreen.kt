package com.example.dentistryapp.ui.doctors

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.dentistryapp.R
import com.example.dentistryapp.ui.model.ReviewUi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DoctorReviewsScreen(
    navController: NavController,
    viewModel: DoctorsViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    val inputError = remember { mutableStateOf(false) }
    var description by rememberSaveable { mutableStateOf("") }
    val reviews by viewModel.reviews.observeAsState(emptyList())
    var ratingState by remember {
        mutableStateOf(0)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) 72.dp else 64.dp,
        spring(Spring.DampingRatioMediumBouncy)
    )
    viewModel.getReviews()
    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Отзывы",
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
                            imageVector = Icons.Filled.Reviews,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                },
                modifier = Modifier
            ) {
                Icon(Icons.Filled.Edit, "Add Review")
            }
        }
    ) { innerPadding ->
        if (openDialog.value) {
            Dialog(
                onDismissRequest = {
                    openDialog.value = false
                    description = ""
                }
            ) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier) {
                        TextField(
                            value = description,
                            onValueChange = {
                                inputError.value = false
                                if (it.length <= 300) description = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp),
                            label = {
                                Text(text = "Отзыв")
                            },
                            supportingText = {
                                Text(
                                    text = "Введите отзыв",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                )
                                Text(
                                    text = "${description.length} / 300",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.End,
                                )
                            },
                            maxLines = 7,
                            isError = inputError.value,
                            trailingIcon = {
                                if (inputError.value) {
                                    Icon(
                                        imageVector = Icons.Filled.Error, "error",
                                        tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            for (i in 1..5) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_round_star_24),
                                    contentDescription = "star",
                                    modifier = Modifier
                                        .width(size)
                                        .height(size)
                                        .pointerInteropFilter {
                                            when (it.action) {
                                                MotionEvent.ACTION_DOWN -> {
                                                    selected = true
                                                    ratingState = i
                                                }

                                                MotionEvent.ACTION_UP -> {
                                                    selected = false
                                                }
                                            }
                                            true
                                        },
                                    tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
                                )
                            }
                        }
                        if (description.isNotEmpty()) {
                            FilledIconButton(
                                onClick = {
                                    viewModel.createReview(ratingState, description)
                                    openDialog.value = false
                                    description = ""
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                content = {
                                    Text(
                                        text = "Оценить",
                                        modifier = Modifier
                                            .wrapContentSize(Alignment.Center)
                                    )
                                }
                            )
                        } else {
                            OutlinedIconButton(
                                onClick = {
                                    inputError.value = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                content = {
                                    Text(
                                        text = "Оценить",
                                        modifier = Modifier
                                            .wrapContentSize(Alignment.Center)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            reviews.forEach {
                item {
                    ReviewItem(item = it)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReviewItem(
    item: ReviewUi
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
            text = item.score,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        Text(
            text = item.description,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    }
}

