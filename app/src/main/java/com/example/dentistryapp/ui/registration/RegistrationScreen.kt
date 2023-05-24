package com.example.dentistryapp.ui.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AppRegistration
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
    navController: NavController,
) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    val nameMaxLength = 30
    var surname by rememberSaveable { mutableStateOf("") }
    val surnameMaxLength = 30
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var repeatPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val phoneNumberError by viewModel.phoneNumberError.observeAsState()
    val nameError by viewModel.nameError.observeAsState()
    val surnameError by viewModel.surnameError.observeAsState()
    val passwordError by viewModel.passwordError.observeAsState()
    val repeatPasswordError by viewModel.repeatPasswordError.observeAsState()
    val screenState by viewModel.registrationScreenState.observeAsState()
    if (screenState == RegistrationScreenState.Registered) {
        viewModel.registered()
        navController.navigate(DentistryNavigationItem.LoginScreen.screenRoute) {
            popUpTo(0)
        }
    } else if (screenState is RegistrationScreenState.OnRegistration) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Регистрация",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Outlined.AppRegistration,
                            contentDescription = "Localized description"
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
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
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                PhoneField(
                    phone = phoneNumber,
                    mask = "+0 (000) 000 00 00",
                    maskNumber = '0',
                    onPhoneChanged = {
                        phoneNumber = it
                        viewModel.removePhoneNumberError()
                    },
                    supportingText = {
                        Text(
                            text = "Введите номер телефона",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                    },
                    isError = phoneNumberError!!,
                    trailingIcon = {
                        if (phoneNumberError!!) {
                            Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = name,
                    onValueChange = {
                        if (it.length <= 30) name = it
                        viewModel.removeNameError()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Имя")
                    },
                    supportingText = {
                        Text(
                            text = "Введите имя",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = "${name.length} / $nameMaxLength",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    },
                    isError = nameError!!,
                    trailingIcon = {
                        if (nameError!!) {
                            Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = surname,
                    onValueChange = {
                        if (it.length <= 30) surname = it
                        viewModel.removeSurnameError()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Фамилия")
                    },
                    supportingText = {
                        Text(
                            text = "Введите фамилию",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = "${surname.length} / $surnameMaxLength",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    },
                    isError = surnameError!!,
                    trailingIcon = {
                        if (surnameError!!) {
                            Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        viewModel.removePasswordError()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Пароль")
                    },
                    supportingText = {
                        Text(
                            text = "Введите пароль",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = passwordError!!,
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = {passwordVisible = !passwordVisible}) {
                            Icon(imageVector  = image, description)
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = repeatPassword,
                    onValueChange = {
                        repeatPassword = it
                        viewModel.removeRepeatPasswordError()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Пароль")
                    },
                    supportingText = {
                        if (repeatPasswordError!!) {
                            Text(
                                text = "Пароли должны совпадать",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                            )
                        }
                    },
                    visualTransformation = if (repeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = repeatPasswordError!!,
                    trailingIcon = {
                        val image = if (repeatPasswordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                        val description = if (repeatPasswordVisible) "Hide password" else "Show password"
                        IconButton(onClick = {repeatPasswordVisible = !repeatPasswordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    }
                )
                FilledIconButton(
                    onClick = {
                        viewModel.register(phoneNumber, name, surname, password, repeatPassword)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
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
    }
}



@Composable
fun PhoneField(
    phone: String,
    modifier: Modifier = Modifier,
    mask: String,
    maskNumber: Char = '0',
    onPhoneChanged: (String) -> Unit,
    supportingText: @Composable (() -> Unit)?,
    isError: Boolean,
    trailingIcon: @Composable (() -> Unit)?
) {
    TextField(
        value = phone,
        onValueChange = { it ->
            onPhoneChanged(it.take(mask.count { it == maskNumber }))
        },
        label = {
            Text(text = "Номер телефона")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(mask, maskNumber),
        modifier = modifier.fillMaxWidth(),
        supportingText = supportingText,
        isError = isError,
        trailingIcon = trailingIcon
    )
}

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        if (maskNumber != other.maskNumber) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar }
}