package com.example.dentistryapp.ui.registration

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dentistryapp.navigation.DentistryNavigationItem

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
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    val phoneNumberError by viewModel.phoneNumberError.observeAsState()
    val nameError by viewModel.nameError.observeAsState()
    val surnameError by viewModel.surnameError.observeAsState()
    val passwordError by viewModel.passwordError.observeAsState()
    val repeatPasswordError by viewModel.repeatPasswordError.observeAsState()
    val registered by viewModel.registered.observeAsState()
    if (registered == true) {
        viewModel.registered()
        navController.navigate(DentistryNavigationItem.LoginScreen.screenRoute) {
            popUpTo(0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
                viewModel.setPhoneNumber(it)
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
                viewModel.setName(it)
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
                viewModel.setSurname(it)
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
            isError = passwordError!!,
            trailingIcon = {
                if (passwordError!!) {
                    Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
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
            isError = repeatPasswordError!!,
            trailingIcon = {
                if (repeatPasswordError!!) {
                    Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
                }
            }
        )
        FilledIconButton(
            onClick = {
                viewModel.register(phoneNumber, name, surname, password, repeatPassword)
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
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



@OptIn(ExperimentalMaterial3Api::class)
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