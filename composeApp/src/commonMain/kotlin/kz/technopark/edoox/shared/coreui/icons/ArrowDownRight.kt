package kz.technopark.edoox.shared.coreui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ArrowDownRight: ImageVector
    get() {
        if (_ArrowDownRight != null) {
            return _ArrowDownRight!!
        }
        _ArrowDownRight = ImageVector.Builder(
            name = "ArrowDownRight",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.293f, 6.707f)
                lineTo(15.586f, 17f)
                horizontalLineTo(9f)
                lineTo(9f, 19f)
                horizontalLineTo(18f)
                curveTo(18.265f, 19f, 18.52f, 18.895f, 18.707f, 18.707f)
                curveTo(18.895f, 18.52f, 19f, 18.265f, 19f, 18f)
                verticalLineTo(9f)
                lineTo(17f, 9f)
                verticalLineTo(15.586f)
                lineTo(6.707f, 5.293f)
                lineTo(5.293f, 6.707f)
                close()
            }
        }.build()

        return _ArrowDownRight!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowDownRight: ImageVector? = null
