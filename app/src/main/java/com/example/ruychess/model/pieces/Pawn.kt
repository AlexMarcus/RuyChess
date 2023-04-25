package com.example.ruychess.model.pieces

import com.example.ruychess.R

class Pawn(override val color: PieceColor) : Piece {

    override val asset: Int
        get() = when (color) {
            PieceColor.Light -> R.drawable.pawn_light
            PieceColor.Dark -> R.drawable.pawn_dark
        }
}