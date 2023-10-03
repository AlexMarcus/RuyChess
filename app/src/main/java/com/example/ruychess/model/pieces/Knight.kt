package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Move
import com.example.ruychess.model.MoveType

@Immutable
class Knight(override val color: PieceColor) : Piece() {

    companion object {
        private val moveSet = arrayOf(
            2 to -1, 2 to 1, 1 to -2, 1 to 2,
            -2 to -1, -2 to 1, -1 to -2, -1 to 2
        )
    }

    override val asset: Int = when (color) {
        PieceColor.Light -> R.drawable.knight_light
        PieceColor.Dark -> R.drawable.knight_dark
    }

    override fun possibleMoves(board: Board): List<Move> {
        val moves = mutableListOf<Move>()

        board.findPosition(this)?.let { position ->
            for (move in moveSet) {
                position.positionByOffset(move.first, move.second)?.also { newPosition ->
                    val pieceAtPosition = board.findPiece(newPosition)
                    when {
                        pieceAtPosition == null -> moves.add(
                            Move(
                                position,
                                newPosition,
                                MoveType.Move
                            )
                        )

                        pieceAtPosition.color != this.color -> moves.add(
                            Move(
                                position,
                                newPosition,
                                MoveType.Capture
                            )
                        )
                    }
                }
            }
        }

        return moves.toList()
    }
}