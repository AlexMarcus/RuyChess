package com.example.ruychess.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ruychess.ChessViewModel
import com.example.ruychess.model.Board
import com.example.ruychess.model.Position
import com.example.ruychess.model.pieces.Piece
import com.example.ruychess.toModifier
import com.example.ruychess.ui.theme.RuyChessTheme

@Composable
fun ChessBoard(
    board: Board,
    viewModel: ChessViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {

        val squareWidth = maxWidth / 8
        board.squares.forEach {
            Square(
                size = squareWidth,
                position = it.position,
                modifier = it.position
                    .toOffset(squareWidth)
                    .toModifier()
            )
            if (it.piece != null) {
                Piece(
                    piece = it.piece,
                    squareSize = squareWidth,
                    modifier = it.position
                        .toOffset(squareWidth)
                        .toModifier()
                )
            }
        }
    }
}

@Composable
fun Square(
    size: Dp,
    position: Position,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                if (position.isLight()) MaterialTheme.colors.primary
                else MaterialTheme.colors.secondary
            )
            .padding(2.dp)
    ) {
        if (position.rank.ordinal == 0) {
            Text(
                text = "${position.file}",
                fontSize = 12.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )
        }
        if (position.file.ordinal == 0) {
            Text(
                text = "${position.rank}",
                fontSize = 12.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
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