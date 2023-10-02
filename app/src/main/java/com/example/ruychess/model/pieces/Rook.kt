package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Position

@Immutable
class Rook(override val color: PieceColor) : LinePiece(
    directions = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(1, 0, 1),
        intArrayOf(0, 1, 0)
    )
) {

    override val asset: Int = when (color) {
            PieceColor.Light -> R.drawable.rook_light
            PieceColor.Dark -> R.drawable.rook_dark
        }
}