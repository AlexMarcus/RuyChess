package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Position

@Immutable
class Queen(override val color: PieceColor) : LinePiece(
    directions = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 0, 1),
        intArrayOf(1, 1, 1)
    )
) {

    override val asset: Int = when (color) {
            PieceColor.Light -> R.drawable.queen_light
            PieceColor.Dark -> R.drawable.queen_dark
        }
}