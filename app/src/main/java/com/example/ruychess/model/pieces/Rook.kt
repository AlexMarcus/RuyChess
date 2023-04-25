package com.example.ruychess.model.pieces

import com.example.ruychess.R

class Rook(override val color: PieceColor) : Piece {

    override val asset: Int
        get() = when (color) {
            PieceColor.Light -> R.drawable.rook_light
            PieceColor.Dark -> R.drawable.rook_dark
        }
}