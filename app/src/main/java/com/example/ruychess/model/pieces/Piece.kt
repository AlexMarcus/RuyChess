package com.example.ruychess.model.pieces

import com.example.ruychess.model.Board
import com.example.ruychess.model.Move

abstract class Piece {
    abstract val asset: Int

    abstract val color: PieceColor

    abstract fun possibleMoves(board: Board): List<Move>

    override fun toString(): String =
        "$color ${this.javaClass.simpleName}"
}
