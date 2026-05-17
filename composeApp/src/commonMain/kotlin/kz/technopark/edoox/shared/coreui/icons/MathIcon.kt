package kz.technopark.edoox.shared.coreui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MathIcon: ImageVector
    get() {
        if (_mathIcon != null) {
            return _mathIcon!!
        }
        _mathIcon = ImageVector.Builder(
            name = "FileFolderIcon",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 1024f,
            viewportHeight = 1024f
        ).apply {

            path(
                fill = SolidColor(Color(0xFFFCE3C3)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(837.768f, 344.266f)
                verticalLineToRelative(447.525f)
                lineTo(283.226f, 791.791f)
                lineTo(283.226f, 195.916f)
                horizontalLineToRelative(394.663f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF300604)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(837.768f, 803.791f)
                lineTo(283.226f, 803.791f)
                lineToRelative(-12f, -12f)
                lineTo(271.226f, 195.916f)
                lineToRelative(12f, -12f)
                horizontalLineToRelative(394.663f)
                lineToRelative(8.162f, 3.203f)
                lineToRelative(159.879f, 148.35f)
                lineToRelative(3.838f, 8.797f)
                verticalLineToRelative(447.525f)
                close()

                moveTo(295.226f, 779.791f)
                horizontalLineToRelative(530.542f)
                verticalLineToRelative(-430.29f)
                lineTo(673.179f, 207.916f)
                lineTo(295.226f, 207.916f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFED8F27)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(677.888f, 195.916f)
                verticalLineToRelative(148.349f)
                horizontalLineToRelative(159.88f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF300604)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(837.768f, 356.266f)
                horizontalLineToRelative(-159.88f)
                lineToRelative(-12f, -12f)
                verticalLineToRelative(-148.35f)
                lineToRelative(20.162f, -8.797f)
                lineToRelative(159.88f, 148.35f)
                close()

                moveTo(689.888f, 332.266f)
                horizontalLineToRelative(117.305f)
                lineTo(689.888f, 223.421f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF300604)),
                stroke = null
            ) {
                moveTo(638.47f, 453.327f)
                horizontalLineToRelative(136.461f)
                verticalLineToRelative(30.664f)
                horizontalLineToRelative(-136.461f)
                close()

                moveTo(638.47f, 521.188f)
                horizontalLineToRelative(136.461f)
                verticalLineToRelative(30.664f)
                horizontalLineToRelative(-136.461f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFB12800)),
                stroke = null
            ) {
                moveTo(138.969f, 425.261f)
                horizontalLineToRelative(352.64f)
                verticalLineToRelative(447.852f)
                horizontalLineToRelative(-352.64f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF300604)),
                stroke = null
            ) {
                moveTo(491.609f, 885.112f)
                horizontalLineToRelative(-352.64f)
                lineToRelative(-12f, -12f)
                lineTo(126.969f, 425.261f)
                lineToRelative(12f, -12f)
                lineTo(491.61f, 413.261f)
                lineToRelative(12f, 12f)
                verticalLineToRelative(447.852f)
                close()

                moveTo(150.969f, 861.112f)
                lineTo(479.61f, 861.112f)
                lineTo(479.61f, 437.261f)
                lineTo(150.969f, 437.261f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF300604)),
                stroke = null
            ) {
                moveTo(187.188f, 479.26f)
                horizontalLineToRelative(253.647f)
                verticalLineToRelative(57.26f)
                horizontalLineToRelative(-253.647f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF228E9D)),
                stroke = null
            ) {
                moveTo(317.533f, 697.232f)
                horizontalLineToRelative(119.938f)
                verticalLineToRelative(131.555f)
                horizontalLineToRelative(-119.938f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFFCE3C3)),
                stroke = null
            ) {
                moveTo(189.617f, 571.187f)
                horizontalLineToRelative(119.938f)
                verticalLineToRelative(131.555f)
                horizontalLineToRelative(-119.938f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFED8F27)),
                stroke = null
            ) {
                moveTo(189.617f, 702.307f)
                horizontalLineToRelative(119.938f)
                verticalLineToRelative(131.555f)
                horizontalLineToRelative(-119.938f)
                close()

                moveTo(317.533f, 574.507f)
                horizontalLineToRelative(119.938f)
                verticalLineToRelative(131.555f)
                horizontalLineToRelative(-119.938f)
                close()
            }

        }.build()

        return _mathIcon!!
    }

private var _mathIcon: ImageVector? = null