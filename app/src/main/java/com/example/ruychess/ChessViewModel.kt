package com.example.ruychess

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ruychess.model.Position
import com.example.ruychess.model.pieces.Pawn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChessViewModel @Inject constructor() : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    fun onClick(position: Position) {
        Log.d(
            javaClass.simpleName,
            "onPieceClick: position = [$position], clickedPosition = [${_gameState.value.clickedPosition}]"
        )
        _gameState.value.let { state ->

            when (state.clickedPosition) {
                position -> {
                    _gameState.value = state.copy(
                        clickedPosition = null,
                        highlightedPositions = listOf(),
                        capturePositions = listOf()
                    )
                }
                null -> {
                    _gameState.value = state.copy(
                        clickedPosition = position,
                        highlightedPositions = state.curBoard.findPiece(position)
                            ?.possibleMoves(state.curBoard)
                            ?.toMovePositions()
                            ?: listOf(),
                        capturePositions = state.curBoard.findPiece(position)
                            ?.possibleMoves(state.curBoard)
                            ?.toCapturePositions()
                            ?: listOf()
                    )
                }
                else -> {
                    val pieceToMove = state.curBoard.findPiece(state.clickedPosition) ?: return
                    if (position in pieceToMove.possibleMoves(state.curBoard).toTargetPositions()) {


                        val currentBoard = state.curBoard.copy()
                        val nextBoard = state.curBoard.copy(
                            pieces = state.curBoard.pieces
                                .minus(state.clickedPosition)
                                .plus(
                                    position to pieceToMove.apply {
                                        if (this is Pawn) hasMoved = true
                                    }
                                )
                        )
                        _gameState.value = state.copy(
                            clickedPosition = null,
                            prevBoard = currentBoard,
                            curBoard = nextBoard,
                            highlightedPositions = listOf(),
                            capturePositions = listOf()
                        )
                    }
                }

            }
        }
    }

}