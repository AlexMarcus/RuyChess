package com.example.ruychess

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ruychess.controller.GameController
import com.example.ruychess.model.Board
import com.example.ruychess.model.Position
import com.example.ruychess.model.pieces.Pawn
import com.example.ruychess.model.pieces.Piece
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChessViewModel @Inject constructor() : ViewModel() {

    private var currentGameController: GameController = GameController()

    val gameState: StateFlow<GameState> = currentGameController.gameState

    fun onClick(position: Position) {
        currentGameController.positionSelected(position)
    }

    fun reset() {
        currentGameController.reset()
    }
}