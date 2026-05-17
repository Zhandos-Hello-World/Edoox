package kz.technopark.edoox.feature.presentation.review.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeableCard(
    onSwipedLeft: () -> Unit,
    onSwipedRight: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .graphicsLayer {
                rotationZ = (offsetX.value / 60f).coerceIn(-15f, 15f)
                alpha = (1f - (kotlin.math.abs(offsetX.value) / 1000f)).coerceIn(0.5f, 1f)
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        if (offsetX.value > 300f) {
                            // Swiped Right animation offscreen
                            scope.launch {
                                offsetX.animateTo(1500f, tween(200))
                                onSwipedRight()
                            }
                        } else if (offsetX.value < -300f) {
                            // Swiped Left animation offscreen
                            scope.launch {
                                offsetX.animateTo(-1500f, tween(200))
                                onSwipedLeft()
                            }
                        } else {
                            // Snap back to center if drag wasn't intentional enough
                            scope.launch {
                                offsetX.animateTo(0f, tween(150))
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            }
    ) {
        content()
    }
}