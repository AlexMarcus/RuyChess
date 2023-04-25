package com.example.ruychess.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = Brown,
    secondary = DarkGreen,
    background = Black,
    surface = LightGrey,
    onPrimary = LightGrey,
    onSecondary = LightGrey,
    onBackground = LightGrey,
    onSurface = LightGrey,
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = LightGreen,
    secondary = Brown,
    background = LightGrey,
    surface = DarkGreen,
    onPrimary = Black,
    onSecondary = LightGrey,
    onBackground = Black,
    onSurface = LightGrey,
)

@Composable
fun RuyChessTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}