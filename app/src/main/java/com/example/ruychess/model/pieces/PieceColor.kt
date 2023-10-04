package com.example.ruychess.model.pieces

enum class PieceColor {
    Light, Dark;

    fun flip(): PieceColor =
        if (this == Light) Dark
        else Light
}