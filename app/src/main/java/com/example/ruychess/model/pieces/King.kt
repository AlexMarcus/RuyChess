package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Move

@Immutable
class King(override val color: PieceColor) : Piece() {

    override val asset: Int = when (color) {
            PieceColor.Light -> R.drawable.king_light
            PieceColor.Dark -> R.drawable.king_dark
        }

    override fun possibleMoves(board: Board): List<Move> = listOf()

}