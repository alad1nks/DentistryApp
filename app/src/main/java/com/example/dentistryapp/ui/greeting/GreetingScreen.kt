package com.example.dentistryapp.ui.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem

@Composable
fun GreetingScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Вы не зарегистрированы",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(24.dp))
        FilledIconButton(
            onClick = {
                navController.navigate(DentistryNavigationItem.LoginScreen.screenRoute)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            content = {
                Text(
                    text = "Войти",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                )
            }
        )
        FilledIconButton(
            onClick = {
                navController.navigate(DentistryNavigationItem.RegistrationScreen.screenRoute)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            content = {
                Text(
                    text = "Зарегистрироваться",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                )
            }
        )
    }
}