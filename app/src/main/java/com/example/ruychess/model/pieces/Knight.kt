package com.example.ruychess.model.pieces

import com.example.ruychess.R

class Knight(override val color: PieceColor) : Piece {

    override val asset: Int
        get() = when (color) {
            PieceColor.Light -> R.drawable.knight_light
            PieceColor.Dark -> R.drawable.knight_dark
        }
}