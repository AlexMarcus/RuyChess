package com.example.ruychess

import com.example.ruychess.model.Board
import com.example.ruychess.model.Position

data class GameState(
    val prevBoard: Board = Board(),
    val curBoard: Board = Board(),
    val clickedPosition: Position? = null
)