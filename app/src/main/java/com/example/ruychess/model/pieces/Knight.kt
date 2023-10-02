package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Move

@Immutable
class Knight(override val color: PieceColor) : Piece() {

    override val asset: Int = when (color) {
            PieceColor.Light -> R.drawable.knight_light
            PieceColor.Dark -> R.drawable.knight_dark
        }

    override fun possibleMoves(board: Board): List<Move> = listOf()
}