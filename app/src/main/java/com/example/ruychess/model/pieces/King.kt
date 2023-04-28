package com.example.ruychess.model.pieces

import com.example.ruychess.R

class King(override val color: PieceColor) : Piece() {

    override val asset: Int
        get() = when (color) {
            PieceColor.Light -> R.drawable.king_light
            PieceColor.Dark -> R.drawable.king_dark
        }
}