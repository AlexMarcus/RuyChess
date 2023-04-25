package com.example.ruychess.model

import android.os.Parcelable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import kotlinx.parcelize.Parcelize
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Parcelize
data class Position(
    val file: File,
    val rank: Rank
) : Parcelable {

    fun isLight(): Boolean {
        //println("$this: ${file.ordinal % 2}, ${rank.ordinal % 2}")
        return file.ordinal % 2 == rank.ordinal % 2
    }

    fun toOffset(squareSize: Dp): Offset = Offset(
        file.ordinal * squareSize.value,
        (7 - rank.ordinal) * squareSize.value
    )

    fun positionByOffset(x: Float, y: Float, squareSize: Dp): Position? {
        val offset = this.toOffset(squareSize)
        println(offset)
        println("x=$x, y=$y, sqsize=${squareSize.value}")
        val newX = offset.x.roundToInt() + x
        val newY = offset.y.roundToInt() + y
        println("newx=${(newX / squareSize.value).roundToInt()}, newy = ${(newY / squareSize.value).roundToInt()}")

        //val newOffset = IntOffset(newX, newY)

        return try {
            Position(
                File.values()[(newX / squareSize.value).roundToInt()],
                Rank.values()[((newY / squareSize.value) - 7).roundToInt().absoluteValue]
            )
        } catch (ex: ArrayIndexOutOfBoundsException) {
            null
        }
    }

    override fun toString(): String {
        return "${file}${rank.intRep}"
    }
}

enum class Rank(val intRep: Int) {
    one(1), two(2), three(3), four(4), five(5), six(6), seven(7), eight(8);

    override fun toString(): String = intRep.toString()
}

enum class File {
    a, b, c, d, e, f, g, h
}