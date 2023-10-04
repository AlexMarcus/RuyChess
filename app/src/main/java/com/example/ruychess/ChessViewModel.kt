package com.example.ruychess

import androidx.lifecycle.ViewModel
import com.example.ruychess.controller.GameController
import com.example.ruychess.model.Position
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChessViewModel @Inject constructor() : ViewModel() {

    private var currentGameController: GameController = GameController()

    val gameState: StateFlow<GameState> = currentGameController.gameUiState

    fun onClick(position: Position) {
        currentGameController.positionSelected(position)
    }

    fun reset() {
        currentGameController.reset()
    }
}