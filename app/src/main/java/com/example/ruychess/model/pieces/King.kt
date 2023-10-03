package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Move
import com.example.ruychess.model.MoveType
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_DefaultViewModelFactories_ActivityEntryPoint

@Immutable
class King(override val color: PieceColor) : Piece() {

    override val asset: Int = when (color) {
        PieceColor.Light -> R.drawable.king_light
        PieceColor.Dark -> R.drawable.king_dark
    }

    override fun possibleMoves(board: Board): List<Move> {
        val moves = mutableListOf<Move>()

        board.findPosition(this)?.let { position ->
            for (i in -1..1) {
                for (j in -1..1) {
                    if (i != 0 || j != 0) {
                        position.positionByOffset(i, j)?.also { newPosition ->
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
            }
        }

        return moves.toList()
    }
}