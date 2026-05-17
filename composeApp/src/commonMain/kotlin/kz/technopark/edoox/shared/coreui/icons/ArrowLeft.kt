package kz.technopark.edoox.shared.coreui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ArrowLeft: ImageVector
    get() {
        if (_ArrowLeft != null) {
            return _ArrowLeft!!
        }
        _ArrowLeft = ImageVector.Builder(
            name = "ArrowLeft",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6.255f, 15.033f)
                lineTo(14.736f, 5.671f)
                lineTo(13.253f, 4.329f)
                lineTo(3.259f, 15.362f)
                curveTo(2.913f, 15.744f, 2.914f, 16.326f, 3.261f, 16.707f)
                lineTo(13.261f, 27.674f)
                lineTo(14.739f, 26.326f)
                lineTo(6.265f, 17.033f)
                lineTo(28f, 17.033f)
                lineTo(28f, 15.033f)
                lineTo(6.255f, 15.033f)
                close()
            }
        }.build()

        return _ArrowLeft!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowLeft: ImageVector? = null
