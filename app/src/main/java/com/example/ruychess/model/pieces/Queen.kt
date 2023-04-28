package com.example.ruychess.model.pieces

import com.example.ruychess.R

class Queen(override val color: PieceColor) : Piece() {

    override val asset: Int
        get() = when (color) {
            PieceColor.Light -> R.drawable.queen_light
            PieceColor.Dark -> R.drawable.queen_dark
        }
}