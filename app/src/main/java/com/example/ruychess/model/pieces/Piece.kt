package com.example.ruychess.model.pieces

abstract class Piece {
    abstract val asset: Int

    abstract val color: PieceColor

    override fun toString(): String =
        "$color ${this.javaClass.simpleName}"
}
