package com.example.ruychess

import android.util.Log
import androidx.lifecycle.ViewModel
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

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    fun onClick(position: Position) {
        Log.d(
            javaClass.simpleName,
            "onPieceClick: position = [$position], clickedPosition = [${_gameState.value.selectedPosition}]"
        )
        _gameState.value.let { prevState ->

            if (prevState.selectedPosition == null) {
                val piece = prevState.curBoard.findPiece(position) ?: return

                _gameState.value =
                    updateStateWithNewPieceSelected(piece, position, prevState.curBoard)
            } else {
                val pieceToMove = prevState.curBoard.findPiece(prevState.selectedPosition) ?: return
                if (position in pieceToMove.possibleMoves(prevState.curBoard).toTargetPositions()) {

                    val currentBoard = prevState.curBoard.copy()
                    val nextBoard = prevState.curBoard.copy(
                        pieces = prevState.curBoard.pieces
                            .minus(prevState.selectedPosition)
                            .plus(
                                position to pieceToMove.apply {
                                    if (this is Pawn) hasMoved = true
                                }
                            )
                    )
                    _gameState.value = prevState.copy(
                        selectedPosition = null,
                        prevBoard = currentBoard,
                        curBoard = nextBoard,
                        highlightedPositions = listOf(),
                        capturePositions = listOf()
                    )
                } else {
                    val piece = prevState.curBoard.findPiece(position)
                    _gameState.value = when (piece) {
                        prevState.curBoard.findPiece(prevState.selectedPosition), null ->
                            _gameState.value.copy(
                                selectedPosition = null,
                                highlightedPositions = listOf(),
                                capturePositions = listOf()
                            )

                        else -> updateStateWithNewPieceSelected(
                            piece, position, prevState.curBoard
                        )
                    }
                }
            }
        }
    }

    private fun updateStateWithNewPieceSelected(
        piece: Piece, position: Position, board: Board
    ): GameState {
        val possibleMoves = piece.possibleMoves(board)

        return _gameState.value.copy(
            selectedPosition = position,
            highlightedPositions = possibleMoves.toMovePositions(),
            capturePositions = possibleMoves.toCapturePositions()
        )
    }
}