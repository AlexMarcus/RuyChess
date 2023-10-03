package com.example.ruychess

import com.example.ruychess.model.Board
import com.example.ruychess.model.Position

data class GameState(
    val prevBoard: Board = Board(),
    val curBoard: Board = Board(),
    val highlightedPositions: List<Position> = listOf(),
    val capturePositions: List<Position> = listOf(),
    val selectedPosition: Position? = null
)