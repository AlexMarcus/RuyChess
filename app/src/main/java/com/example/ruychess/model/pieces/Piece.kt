package com.example.ruychess.model.pieces

import com.example.ruychess.model.Board
import com.example.ruychess.model.Move

abstract class Piece {
    abstract val asset: Int

    abstract val color: PieceColor

    abstract fun possibleMoves(board: Board): List<Move>

    override fun toString(): String =
        "$color ${this.javaClass.simpleName}"

//    override fun equals(other: Any?): Boolean {
//        if (other is Piece) {
//            return other.asset == this.asset
//                    && other.color == this.color
//        }
//
//        return false
//    }
//
//    override fun hashCode(): Int {
//        var result = asset
//        result = 31 * result + color.hashCode()
//        return result
//    }


}
