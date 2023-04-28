package com.example.ruychess.model.pieces

import com.example.ruychess.R

class Bishop(override val color: PieceColor) : Piece() {

    override val asset: Int
        get() = when (color) {
            PieceColor.Light -> R.drawable.bishop_light
            PieceColor.Dark -> R.drawable.bishop_dark
        }
}