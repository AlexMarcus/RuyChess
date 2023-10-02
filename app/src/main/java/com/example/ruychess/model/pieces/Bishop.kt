package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R

@Immutable
class Bishop(override val color: PieceColor) : LinePiece(
    directions = arrayOf(
        intArrayOf(1, 0, 1),
        intArrayOf(0, 0, 0),
        intArrayOf(1, 0, 1)
    )
) {

    override val asset: Int = when (color) {
        PieceColor.Light -> R.drawable.bishop_light
        PieceColor.Dark -> R.drawable.bishop_dark
    }
}