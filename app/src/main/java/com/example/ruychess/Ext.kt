package com.example.ruychess

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.example.ruychess.model.Move
import com.example.ruychess.model.MoveType
import com.example.ruychess.model.Position

fun Offset.toModifier(): Modifier = Modifier.offset(
    Dp(x),
    Dp(y)
)

fun List<Move>.toTargetPositions(): List<Position> = this.map { it.to }

fun List<Move>.toMovePositions(): List<Position> =
    this.filter { it.type == MoveType.Move }.map { it.to }

fun List<Move>.toCapturePositions(): List<Position> =
    this.filter { it.type == MoveType.Capture }.map { it.to }