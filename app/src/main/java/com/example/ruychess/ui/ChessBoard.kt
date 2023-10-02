package com.example.ruychess.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ruychess.ChessViewModel
import com.example.ruychess.model.Board
import com.example.ruychess.model.Position
import com.example.ruychess.model.Square
import com.example.ruychess.model.pieces.Piece
import com.example.ruychess.model.pieces.Pieces
import com.example.ruychess.toModifier
import com.example.ruychess.ui.theme.DarkSquareColor
import com.example.ruychess.ui.theme.LightSquareColor
import com.example.ruychess.ui.theme.RuyChessTheme

@Composable
fun ChessBoard(
    board: Board,
    viewModel: ChessViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    println("recomposing board")

    val state by viewModel.gameState.collectAsState()

    //println(state.clickedPosition)
    val positionClickEvent = remember(viewModel) {
        object : UiEvent<Position> {
            override fun onClick(clickedItem: Position) {
                viewModel.onClick(clickedItem)
            }
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val squareSize = maxWidth / 8
        state.curBoard.squares.forEach { square ->
            DecoratedSquare(
                square = square,
                squareSize = squareSize,
                isHighlighted = square.position in state.highlightedPositions,
                isCapture = square.position in state.capturePositions,
                clickedPosition = state.clickedPosition,
                uiEvent = positionClickEvent
            )
        }
        Pieces(state.curBoard.pieces, squareSize)
    }
}

@Composable
fun Pieces(
    pieces: Pieces,
    squareSize: Dp
) {
    pieces.forEach { (position, piece) ->
        key(piece) {
            AnimatedPiece(position, piece, squareSize)
        }
    }
}

@Composable
fun AnimatedPiece(
    position: Position,
    piece: Piece,
    squareSize: Dp
) {
    val offset by animateOffsetAsState(
        targetValue = position.toOffset(squareSize),
        animationSpec = tween(1000, easing = LinearEasing)
    )
    Piece(piece = piece, squareSize = squareSize, modifier = offset.toModifier())
}

@Composable
fun DecoratedSquare(
    square: Square,
    squareSize: Dp,
    clickedPosition: Position?,
    isHighlighted: Boolean,
    isCapture: Boolean,
    uiEvent: UiEvent<Position>,
    modifier: Modifier = Modifier
) {
    Square(
        size = squareSize,
        position = square.position,
        isHighlighted = isHighlighted,
        isCapture = isCapture,
        clickedPosition = clickedPosition,
        modifier = square.position
            .toOffset(squareSize)
            .toModifier()
            .clickable {
                uiEvent.onClick(square.position)
            }
    )
}

@Composable
fun Square(
    size: Dp,
    position: Position,
    isHighlighted: Boolean,
    isCapture: Boolean,
    clickedPosition: Position?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .background(if (position.isLight()) LightSquareColor else DarkSquareColor)
    ) {
        if (position.rank.ordinal == 0) {
            Text(
                text = "${position.file}",
                fontSize = 12.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(2.dp)
            )
        }
        if (position.file.ordinal == 0) {
            Text(
                text = "${position.rank}",
                fontSize = 12.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(2.dp)
            )
        }

        if (clickedPosition == position) {
            Canvas(Modifier.fillMaxSize(),
                onDraw = {
                    drawRect(
                        color = Color.LightGray,
                        alpha = 0.55f
                    )
                }
            )
        }

        if (isHighlighted) {
            Canvas(
                Modifier
                    .fillMaxSize(0.4f)
                    .align(Alignment.Center),
                onDraw = {
                    drawCircle(
                        color = Color.LightGray,
                        alpha = 0.65f
                    )
                }
            )
        }

        if (isCapture) {
            Canvas(
                Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center),
                onDraw = {
                    drawCircle(
                        color = Color.LightGray,
                        alpha = 0.65f,
                        style = Stroke(this.size.minDimension / 8f)
                    )
                }
            )
        }
    }
}

@Composable
fun Piece(
    piece: Piece,
    squareSize: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(squareSize, squareSize),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = piece.asset),
            contentDescription = piece::class.java.simpleName,
            tint = Color.Unspecified
        )
    }
}


@Preview
@Composable
fun BoardPreview() {
    RuyChessTheme {
        ChessBoard(
            Board()
        )
    }
}

/*
 * Draggable piece


//@Composable
//fun Piece(
//    piece: Piece,
//    squareSize: Dp,
//    onDrag: (Float, Float) -> Unit,
//    modifier: Modifier
//) {
//    //println("RECOMPOSING 2")
//    var offsetX by remember { mutableStateOf(0f) }
//    var offsetY by remember { mutableStateOf(0f) }
//    var scale by remember { mutableStateOf(1f) }
//
//    Box(
//        modifier = modifier
//            .size(squareSize, squareSize)
//            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragStart = {
//                        offsetY = -80f
//                    },
//                    onDrag = { change, dragAmount ->
//                        change.consume()
//                        offsetX += dragAmount.x
//                        offsetY += dragAmount.y
//                        scale = 1.8f
//                    },
//                    onDragEnd = {
//                        onDrag(offsetX, offsetY)
//                        offsetX = 0f
//                        offsetY = 0f
//                        scale = 1f
//                    }
//                )
//            }
//    ) {
//        Icon(
//            modifier = Modifier.scale(scale),
//            painter = painterResource(id = piece.asset),
//            contentDescription = piece::class.java.simpleName
//        )
//    }
//}


* */