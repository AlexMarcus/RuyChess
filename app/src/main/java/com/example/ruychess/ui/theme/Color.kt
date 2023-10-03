package com.example.ruychess.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightGrey = Color(0xFFD1D5DE)
val Grey = Color(0xFFB7B6C2)
val Brown = Color(0xFF837569)
val Pink = Color(0xFFAA336A)
val Yellow = Color(0xFFFDFD96)

val LightGreen = Color(0xFF8AAA79)
val Green = Color(0xFF657153)
val DarkGreen = Color(0xFF19231A)

val DarkBlue = Color(0xFF0D1321)
val DarkPurple = Color(0xFF2B193D)
val Black = Color(0xFF090302)
val SmokeyBlack = Color(0xFF19180A)

@get:Composable
val Colors.lightSquareColor: Color
    get() = if (isLight) LightGrey else DarkPurple

@get:Composable
val Colors.darkSquareColor: Color
    get() = if (isLight) LightGreen else Black

