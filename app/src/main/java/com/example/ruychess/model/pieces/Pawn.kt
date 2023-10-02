package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Move
import com.example.ruychess.model.MoveType

@Immutable
class Pawn(override val color: PieceColor) : Piece() {

    var hasMoved: Boolean = false

    override val asset: Int = when (color) {
        PieceColor.Light -> R.drawable.pawn_light
        PieceColor.Dark -> R.drawable.pawn_dark
    }

    override fun possibleMoves(board: Board): List<Move> {
        val moves = mutableListOf<Move>()
        val direction = if (color == PieceColor.Dark) -1 else 1

        board.findPosition(this)?.let { position ->
            val frontPosition = position.positionByOffset(0, 1 * direction)
            val pieceFront = frontPosition?.let {
                board.findPiece(it)
            }
            val front2Position = position.positionByOffset(0, 2 * direction)
            val piece2Front = front2Position?.let {
                board.findPiece(it)
            }
            val diagPieces = listOf(
                position.positionByOffset(-1, 1 * direction)?.let { board.findPiece(it) },
                position.positionByOffset(1, 1 * direction)?.let { board.findPiece(it) },
            )

            if (pieceFront == null && frontPosition != null) {
                moves.add(Move(position, frontPosition, MoveType.Move))
            }

            if (!hasMoved && piece2Front == null && front2Position != null) {
                moves.add(Move(position, front2Position, MoveType.Move))
            }

            for (piece in diagPieces) {
                if (piece != null) {
                    val piecePos = board.findPosition(piece)
                    if (piecePos != null && piece.color != this.color) {
                        moves.add(Move(position, piecePos, MoveType.Capture))
                    }
                }
            }

        }
        return moves.toList()
    }
}