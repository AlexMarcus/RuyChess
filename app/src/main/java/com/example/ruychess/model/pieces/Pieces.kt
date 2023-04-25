package com.example.ruychess.model.pieces

import com.example.ruychess.model.Position

class Pieces(
    private val pieces: Map<Position, Piece>
) {
    operator fun get(position: Position): Piece? =
        pieces.entries.firstOrNull { it.key == position }?.value
}