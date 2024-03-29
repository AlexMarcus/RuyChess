package com.example.ruychess.model.pieces

import com.example.ruychess.model.Board
import com.example.ruychess.model.File
import com.example.ruychess.model.Move
import com.example.ruychess.model.MoveType
import com.example.ruychess.model.Position
import com.example.ruychess.model.Rank

abstract class LinePiece(private val directions: Array<IntArray>) : Piece() {

    init {
        // directions array must be 3x3
        require(directions.size == 3 && directions[0].size == 3) { "directions must be 3x3" }
    }

    override fun possibleMoves(board: Board): List<Move> {

        val moves = mutableListOf<Move>()

        board.findPosition(this)?.let { position ->
            for (i in directions.indices) {
                for (j in directions[0].indices) {
                    if (directions[i][j] == 1) {
                        val rankDelta = when (i) {
                            0 -> 1
                            1 -> 0
                            2 -> -1
                            else -> 0
                        }

                        val fileDelta = when (j) {
                            0 -> -1
                            1 -> 0
                            2 -> 1
                            else -> 0
                        }

                        var newPosition = position.positionByOffset(fileDelta, rankDelta)
                        while (newPosition != null) {
                            val pieceAtSquare = board.findPiece(newPosition)
                            if (pieceAtSquare != null) {
                                if (pieceAtSquare.color != this.color) {
                                    moves += Move(position, newPosition, MoveType.Capture)
                                }
                                break
                            }
                            moves += Move(position, newPosition)

                            newPosition = newPosition.positionByOffset(fileDelta, rankDelta)
                        }
                    }
                }
            }
        }

        return moves.toList()
    }
}