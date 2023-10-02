package com.example.ruychess.model

data class Move(
    val from: Position,
    val to: Position,
    val type: MoveType = MoveType.Move
)

enum class MoveType {
    Move, Capture, Promotion
}