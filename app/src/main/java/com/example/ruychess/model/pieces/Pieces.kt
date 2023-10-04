package com.example.ruychess.model.pieces

import androidx.compose.runtime.Immutable
import com.example.ruychess.model.Position

@Immutable
data class Pieces(
    val pieces: Map<Position, Piece>
) : Iterable<Map.Entry<Position, Piece>>{
    operator fun get(position: Position): Piece? =
        pieces.entries.firstOrNull { it.key == position }?.value

    operator fun minus(position: Position): Pieces =
        Pieces(pieces.minus(position))

    operator fun plus(entry: Pair<Position, Piece>): Pieces =
        Pieces(pieces.plus(entry))

    fun get(pieceColor: PieceColor): Map<Position, Piece> = pieces.filter {
        it.value.color == pieceColor
    }

    override fun iterator(): Iterator<Map.Entry<Position, Piece>> = pieces.iterator()
}