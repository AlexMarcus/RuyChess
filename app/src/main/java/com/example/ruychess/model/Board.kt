package com.example.ruychess.model

import com.example.ruychess.model.pieces.Bishop
import com.example.ruychess.model.pieces.King
import com.example.ruychess.model.pieces.Knight
import com.example.ruychess.model.pieces.Pawn
import com.example.ruychess.model.pieces.Piece
import com.example.ruychess.model.pieces.PieceColor
import com.example.ruychess.model.pieces.Pieces
import com.example.ruychess.model.pieces.Queen
import com.example.ruychess.model.pieces.Rook

data class Board(
    val pieces: Pieces = initialPieces
) {
    val squares: List<Square>

    init {
        squares = createSquares()
        println(squares)
    }

    private fun createSquares(): List<Square> {
        val squareList = mutableListOf<Square>()
        for (file in File.values()) {
            for (rank in Rank.values()) {
                val position = Position(file, rank)
                squareList.add(Square(position, pieces[position]))
            }
        }
        return squareList
    }

    fun findPiece(position: Position): Piece? =
        squares.firstOrNull { it.position == position }?.piece
}

private val initialPieces = Pieces(
    mapOf(
        Position(File.a, Rank.one) to Rook(PieceColor.Light),
        Position(File.b, Rank.one) to Knight(PieceColor.Light),
        Position(File.c, Rank.one) to Bishop(PieceColor.Light),
        Position(File.d, Rank.one) to Queen(PieceColor.Light),
        Position(File.e, Rank.one) to King(PieceColor.Light),
        Position(File.f, Rank.one) to Bishop(PieceColor.Light),
        Position(File.g, Rank.one) to Knight(PieceColor.Light),
        Position(File.h, Rank.one) to Rook(PieceColor.Light),
        Position(File.a, Rank.two) to Pawn(PieceColor.Light),
        Position(File.b, Rank.two) to Pawn(PieceColor.Light),
        Position(File.c, Rank.two) to Pawn(PieceColor.Light),
        Position(File.d, Rank.two) to Pawn(PieceColor.Light),
        Position(File.e, Rank.two) to Pawn(PieceColor.Light),
        Position(File.f, Rank.two) to Pawn(PieceColor.Light),
        Position(File.g, Rank.two) to Pawn(PieceColor.Light),
        Position(File.h, Rank.two) to Pawn(PieceColor.Light),

        Position(File.a, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.b, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.c, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.d, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.e, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.f, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.g, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.h, Rank.seven) to Pawn(PieceColor.Dark),
        Position(File.a, Rank.eight) to Rook(PieceColor.Dark),
        Position(File.b, Rank.eight) to Knight(PieceColor.Dark),
        Position(File.c, Rank.eight) to Bishop(PieceColor.Dark),
        Position(File.d, Rank.eight) to Queen(PieceColor.Dark),
        Position(File.e, Rank.eight) to King(PieceColor.Dark),
        Position(File.f, Rank.eight) to Bishop(PieceColor.Dark),
        Position(File.g, Rank.eight) to Knight(PieceColor.Dark),
        Position(File.h, Rank.eight) to Rook(PieceColor.Dark),
    )
)