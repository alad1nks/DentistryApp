package com.example.dentistryapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.dentistryapp.ui.profile.ProfileViewModel


private val dentistryDarkColorScheme = darkColorScheme(
    primary = dentistryDarkPrimary,
    onPrimary = dentistryDarkOnPrimary,
    primaryContainer = dentistryDarkPrimaryContainer,
    onPrimaryContainer = dentistryDarkOnPrimaryContainer,
    inversePrimary = dentistryDarkPrimaryInverse,
    secondary = dentistryDarkSecondary,
    onSecondary = dentistryDarkOnSecondary,
    secondaryContainer = dentistryDarkSecondaryContainer,
    onSecondaryContainer = dentistryDarkOnSecondaryContainer,
    tertiary = dentistryDarkTertiary,
    onTertiary = dentistryDarkOnTertiary,
    tertiaryContainer = dentistryDarkTertiaryContainer,
    onTertiaryContainer = dentistryDarkOnTertiaryContainer,
    error = dentistryDarkError,
    onError = dentistryDarkOnError,
    errorContainer = dentistryDarkErrorContainer,
    onErrorContainer = dentistryDarkOnErrorContainer,
    background = dentistryDarkBackground,
    onBackground = dentistryDarkOnBackground,
    surface = dentistryDarkSurface,
    onSurface = dentistryDarkOnSurface,
    inverseSurface = dentistryDarkInverseSurface,
    inverseOnSurface = dentistryDarkInverseOnSurface,
    surfaceVariant = dentistryDarkSurfaceVariant,
    onSurfaceVariant = dentistryDarkOnSurfaceVariant,
    outline = dentistryDarkOutline
)

private val dentistryLightColorScheme = lightColorScheme(
    primary = dentistryLightPrimary,
    onPrimary = dentistryLightOnPrimary,
    primaryContainer = dentistryLightPrimaryContainer,
    onPrimaryContainer = dentistryLightOnPrimaryContainer,
    inversePrimary = dentistryLightPrimaryInverse,
    secondary = dentistryLightSecondary,
    onSecondary = dentistryLightOnSecondary,
    secondaryContainer = dentistryLightSecondaryContainer,
    onSecondaryContainer = dentistryLightOnSecondaryContainer,
    tertiary = dentistryLightTertiary,
    onTertiary = dentistryLightOnTertiary,
    tertiaryContainer = dentistryLightTertiaryContainer,
    onTertiaryContainer = dentistryLightOnTertiaryContainer,
    error = dentistryLightError,
    onError = dentistryLightOnError,
    errorContainer = dentistryLightErrorContainer,
    onErrorContainer = dentistryLightOnErrorContainer,
    background = dentistryLightBackground,
    onBackground = dentistryLightOnBackground,
    surface = dentistryLightSurface,
    onSurface = dentistryLightOnSurface,
    inverseSurface = dentistryLightInverseSurface,
    inverseOnSurface = dentistryLightInverseOnSurface,
    surfaceVariant = dentistryLightSurfaceVariant,
    onSurfaceVariant = dentistryLightOnSurfaceVariant,
    outline = dentistryLightOutline
)

@Composable
fun DentistryTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    viewModel: ProfileViewModel,
    content: @Composable () -> Unit
) {
    val darkTheme by viewModel.darkMode.observeAsState()
    val dentistryColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme == true) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme == true -> dentistryDarkColorScheme
        else -> dentistryLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = dentistryColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme!!
        }
    }

    MaterialTheme(
        colorScheme = dentistryColorScheme,
//        typography = replyTypography,
        shapes = shapes,
        content = content
    )
}