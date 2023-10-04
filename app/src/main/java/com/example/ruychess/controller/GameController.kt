package com.example.ruychess.controller

import com.example.ruychess.GameState
import com.example.ruychess.model.Board
import com.example.ruychess.model.MoveType
import com.example.ruychess.model.Position
import com.example.ruychess.model.pieces.King
import com.example.ruychess.model.pieces.Pawn
import com.example.ruychess.model.pieces.Piece
import com.example.ruychess.model.pieces.PieceColor
import com.example.ruychess.toCapturePositions
import com.example.ruychess.toMovePositions
import com.example.ruychess.toTargetPositions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

/**
 * Controls the flow of the chess game. Contains a UI State along with a Game State.
 *
 * Plan on having some initialization with existing games and stuff.
 * Plan on having forward and backward move functionality.
 */
class GameController {

    private val _gameUiState = MutableStateFlow(GameState())
    val gameUiState: StateFlow<GameState> = _gameUiState.asStateFlow()

    private var toMove: PieceColor = PieceColor.Light

    fun positionSelected(position: Position) {
        _gameUiState.value.let { prevState ->
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
                            _gameUiState.value = _gameUiState.value.copy(
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

        // Check to see if there are any checks
        val hasCheck = checkCheck(nextBoard)
        if(hasCheck) Timber.i("$toMove is checking the ${toMove.flip()} King")

        toMove = toMove.flip()

        _gameUiState.value = prevState.copy(
            selectedPosition = null,
            prevBoard = currentBoard,
            curBoard = nextBoard,
            highlightedPositions = listOf(),
            capturePositions = listOf()
        )
    }

    private fun checkCheck(board: Board): Boolean {
        // get all pieces of the color that just moved
        val curMovePieces = board.pieces.get(toMove)

        for (piece in curMovePieces) {
            if (
                piece.value.possibleMoves(board).any {
                    it.type == MoveType.Capture
                            && board.findPiece(it.to) is King
                }
            ) return true
        }

        return false
    }

    private fun updateStateWithNewPieceSelected(
        piece: Piece?, position: Position, board: Board
    ) {
        if (piece == null || piece.color != toMove) return

        val possibleMoves = piece.possibleMoves(board)
        //.withCheckConstraints(board)

        _gameUiState.value = _gameUiState.value.copy(
            selectedPosition = position,
            highlightedPositions = possibleMoves.toMovePositions(),
            capturePositions = possibleMoves.toCapturePositions()
        )
    }

    fun reset() {
        _gameUiState.value = GameState()
        toMove = PieceColor.Light
    }

}