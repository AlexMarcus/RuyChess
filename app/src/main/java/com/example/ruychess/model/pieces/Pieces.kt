package com.example.ruychess.model.pieces

import com.example.ruychess.model.Position

class Pieces(
    val pieces: Map<Position, Piece>
) : Iterable<Map.Entry<Position, Piece>>{
    operator fun get(position: Position): Piece? =
        pieces.entries.firstOrNull { it.key == position }?.value

    operator fun minus(position: Position): Pieces =
        Pieces(pieces.minus(position))

    operator fun plus(entry: Pair<Position, Piece>): Pieces =
        Pieces(pieces.plus(entry))

    override fun iterator(): Iterator<Map.Entry<Position, Piece>> = pieces.iterator()
}