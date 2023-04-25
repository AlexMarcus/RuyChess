package com.example.ruychess.model

import com.example.ruychess.model.pieces.Piece

data class Square(
    val position: Position,
    val piece: Piece?
)