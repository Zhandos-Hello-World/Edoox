package kz.technopark.edoox.shared.coreui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HistoryApiIcon: ImageVector
    get() {
        if (_historyApiIcon != null) {
            return _historyApiIcon!!
        }

        _historyApiIcon = ImageVector.Builder(
            name = "HistoryApiIcon",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 73f,
            viewportHeight = 73f
        ).apply {


            // Main manuscript body
            path(
                fill = SolidColor(Color(0xFF845151)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(10f, 20f)
                lineTo(58f, 20f)
                lineTo(58f, 54f)
                lineTo(10f, 54f)
                close()
            }

            // Inner paper
            path(
                fill = SolidColor(Color(0xFFEEDFDA)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(17f, 26f)
                lineTo(50f, 26f)
                lineTo(50f, 48f)
                lineTo(17f, 48f)
                close()
            }

            // Orange bookmark / flame shape
            path(
                fill = SolidColor(Color(0xFFFA6931)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(40f, 47f)
                curveTo(42f, 45f, 43f, 43f, 43f, 41f)
                curveTo(43f, 39f, 42f, 37f, 40f, 36f)
                curveTo(39f, 35f, 38f, 34f, 38f, 32f)
                lineTo(38f, 30f)
                lineTo(41f, 30f)
                lineTo(41f, 32f)
                curveTo(41f, 33f, 42f, 34f, 43f, 35f)
                curveTo(45f, 36f, 46f, 38f, 46f, 41f)
                curveTo(46f, 44f, 44f, 46f, 42f, 47f)
                curveTo(41f, 48f, 41f, 49f, 41f, 50f)
                lineTo(41f, 53f)
                lineTo(38f, 53f)
                lineTo(38f, 50f)
                curveTo(38f, 49f, 39f, 48f, 40f, 47f)
                close()
            }

            // Left orange symbol
            path(
                fill = SolidColor(Color(0xFFF1562E)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20f, 32f)
                curveTo(20f, 30f, 22f, 28f, 24f, 28f)
                lineTo(27f, 28f)
                curveTo(28.5f, 28f, 30f, 29.5f, 30f, 31f)
                curveTo(30f, 32f, 29.5f, 33f, 28.5f, 34f)
                curveTo(29.5f, 35f, 30f, 36f, 30f, 37f)
                curveTo(30f, 39f, 28.5f, 40.5f, 26.5f, 40.5f)
                lineTo(22f, 40.5f)
                lineTo(22f, 43f)
                lineTo(20f, 43f)
                close()
            }

            // Grey text lines
            path(
                fill = SolidColor(Color(0xFFC8C1C9)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(36f, 30f)
                lineTo(43f, 30f)
                lineTo(43f, 31.5f)
                lineTo(36f, 31.5f)
                close()

                moveTo(36f, 35f)
                lineTo(47f, 35f)
                lineTo(47f, 36.5f)
                lineTo(36f, 36.5f)
                close()

                moveTo(36f, 40f)
                lineTo(49f, 40f)
                lineTo(49f, 41.5f)
                lineTo(36f, 41.5f)
                close()
            }

        }.build()

        return _historyApiIcon!!
    }

private var _historyApiIcon: ImageVector? = null