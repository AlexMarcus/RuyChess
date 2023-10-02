package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.R
import com.example.ruychess.model.Board
import com.example.ruychess.model.Move

@Immutable
class Pawn(override val color: PieceColor) : Piece() {

    var hasMoved: Boolean = false

    override val asset: Int = when (color) {
            PieceColor.Light -> R.drawable.pawn_light
            PieceColor.Dark -> R.drawable.pawn_dark
        }

    override fun possibleMoves(board: Board): List<Move> = listOf()
//    {
//
//        board.findPosition(this)?.let { positionOfPiece ->
//            if(position.file != positionOfPiece.file) return false
//
//            return if(color == PieceColor.Dark){
//                positionOfPiece.rank.distanceTo(position.rank) == -1 ||
//                        (!hasMoved && positionOfPiece.rank.distanceTo(position.rank) == -2)
//            } else {
//                positionOfPiece.rank.distanceTo(position.rank) == 1 ||
//                        (!hasMoved && positionOfPiece.rank.distanceTo(position.rank) == 2)
//            }
//        } ?: return false
//    }
}