package com.example.ruychess

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp

fun Offset.toModifier(): Modifier = Modifier.offset(
    Dp(x),
    Dp(y)
)