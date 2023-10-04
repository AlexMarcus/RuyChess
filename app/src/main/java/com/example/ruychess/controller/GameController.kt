package com.example.ruychess.controller

import com.example.ruychess.GameState
import com.example.ruychess.model.Board
import com.example.ruychess.model.Position
import com.example.ruychess.model.pieces.Pawn
import com.example.ruychess.model.pieces.Piece
import com.example.ruychess.model.pieces.PieceColor
import com.example.ruychess.toCapturePositions
import com.example.ruychess.toMovePositions
import com.example.ruychess.toTargetPositions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Controls the flow of the chess game. Contains a UI State along with a Game State.
 *
 * Plan on having some initialization with existing games and stuff.
 * Plan on having forward and backward move functionality.
 */
class GameController() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var toMove: PieceColor = PieceColor.Light

    fun positionSelected(position: Position) {
        _gameState.value.let { prevState ->
            if (prevState.selectedPosition == null) {
                val piece = prevState.curBoard.findPiece(position)
                updateStateWithNewPieceSelected(piece, position, prevState.curBoard)

            } else {
                val pieceToMove = prevState.curBoard.findPiece(prevState.selectedPosition) ?: return
                if (position in pieceToMove.possibleMoves(prevState.curBoard).toTargetPositions()) {
                    performMove(prevState, prevState.selectedPosition, position, pieceToMove)
                } else {
                    when (val piece = prevState.curBoard.findPiece(position)) {
                        prevState.curBoard.findPiece(prevState.selectedPosition), null ->
                            _gameState.value = _gameState.value.copy(
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

    private fun performMove(
        prevState: GameState,
        prevPosition: Position,
        position: Position,
        pieceToMove: Piece
    ) {
        val currentBoard = prevState.curBoard.copy()
        val nextBoard = prevState.curBoard.copy(
            pieces = prevState.curBoard.pieces
                .minus(prevPosition)
                .plus(position to pieceToMove)
        )

        if (pieceToMove is Pawn) pieceToMove.hasMoved = true

        toMove = toMove.flip()

        _gameState.value = prevState.copy(
            selectedPosition = null,
            prevBoard = currentBoard,
            curBoard = nextBoard,
            highlightedPositions = listOf(),
            capturePositions = listOf()
        )
    }

    private fun updateStateWithNewPieceSelected(
        piece: Piece?, position: Position, board: Board
    ){
        if(piece == null || piece.color != toMove) return

        val possibleMoves = piece.possibleMoves(board)

        _gameState.value = _gameState.value.copy(
            selectedPosition = position,
            highlightedPositions = possibleMoves.toMovePositions(),
            capturePositions = possibleMoves.toCapturePositions()
        )
    }

    fun reset() {
        _gameState.value = GameState()
        toMove = PieceColor.Light
    }

}