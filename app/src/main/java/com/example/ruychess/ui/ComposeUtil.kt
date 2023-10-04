package com.example.ruychess.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import kotlin.math.sqrt

/**
 * Animate and offset with a constant velocity.
 *
 * https://stackoverflow.com/questions/77218423/in-jetpack-compose-can-i-configure-an-animation-to-run-with-a-constant-velocity
 */
@Composable
fun animateConstantSpeedOffsetAsState(
    initialOffset: Offset = Offset.Zero,
    targetOffset: Offset,
    velocity: Float,
    finishedListener: ((Offset) -> Unit)? = null
): State<Offset> {

    require(velocity > 0f)

    var previousOffset by remember {
        mutableStateOf(initialOffset)
    }

    val durationMillis by remember {
        mutableStateOf(calculateDuration(targetOffset, previousOffset, velocity))
    }.apply {
        val duration = calculateDuration(targetOffset, previousOffset, velocity)
        if (duration > 0) {
            this.value = duration
        }
    }

    previousOffset = targetOffset

    val animationSpec = tween<Offset>(
        durationMillis = durationMillis,
        easing = LinearEasing
    )
    return animateOffsetAsState(
        targetOffset,
        animationSpec,
        finishedListener = {
            previousOffset = targetOffset
            finishedListener?.invoke(it)
        }
    )
}

private fun calculateDuration(
    initialValue: Offset,
    targetValue: Offset,
    velocity: Float
): Int {
    val deltaX = targetValue.x - initialValue.x
    val deltaY = targetValue.y - initialValue.y

    val distance = sqrt(deltaX * deltaX + deltaY * deltaY)
    return (distance / velocity * 1000).toInt()
}