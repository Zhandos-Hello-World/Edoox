package kz.technopark.edoox.shared.coreui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val BiologyIcon: ImageVector
    get() {
        if (_biologyIcon != null) {
            return _biologyIcon!!
        }

        _biologyIcon = ImageVector.Builder(
            name = "NodesIcon",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 504.737f,
            viewportHeight = 504.737f
        ).apply {

            // Green connection lines
            path(
                fill = SolidColor(Color(0xFF2CD282)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(93.648f, 224.635f)
                curveToRelative(-1.707f, 0f, -3.413f, 0f, -4.267f, -0.853f)
                lineToRelative(-76.8f, -46.933f)
                curveToRelative(-4.267f, -2.56f, -5.12f, -7.68f, -2.56f, -11.947f)
                reflectiveCurveToRelative(7.68f, -5.12f, 11.947f, -2.56f)
                lineToRelative(72.533f, 43.52f)
                lineToRelative(76.8f, -49.493f)
                verticalLineToRelative(-89.6f)
                curveToRelative(0f, -5.12f, 3.413f, -8.533f, 8.533f, -8.533f)
                reflectiveCurveToRelative(8.533f, 3.413f, 8.533f, 8.533f)
                verticalLineToRelative(93.867f)
                curveToRelative(0f, 2.56f, -1.707f, 5.973f, -4.267f, 6.827f)
                lineToRelative(-85.333f, 55.467f)
                curveToRelative(-1.706f, 0.853f, -3.413f, 1.706f, -5.12f, 1.706f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF2CD282)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(170.449f, 446.502f)
                curveToRelative(-5.12f, 0f, -8.533f, -3.413f, -8.533f, -8.533f)
                verticalLineToRelative(-89.6f)
                lineToRelative(-76.8f, -49.493f)
                lineToRelative(-71.68f, 52.053f)
                curveToRelative(-4.267f, 2.56f, -9.387f, 1.707f, -11.947f, -1.707f)
                curveToRelative(-2.56f, -4.267f, -1.707f, -9.387f, 1.707f, -12.8f)
                lineToRelative(76.8f, -55.467f)
                curveToRelative(2.56f, -1.707f, 6.827f, -2.56f, 9.387f, 0f)
                lineToRelative(85.333f, 55.467f)
                curveToRelative(2.56f, 1.707f, 4.267f, 4.267f, 4.267f, 6.827f)
                verticalLineToRelative(94.72f)
                curveToRelative(0f, 5.12f, -3.413f, 8.533f, -8.534f, 8.533f)
                close()
            }

            // Pink connection lines
            path(
                fill = SolidColor(Color(0xFFCC66A1)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(330.022f, 449.062f)
                curveToRelative(-5.12f, 0f, -8.533f, -3.413f, -8.533f, -8.533f)
                verticalLineToRelative(-94.72f)
                curveToRelative(0f, -2.56f, 1.707f, -5.973f, 4.267f, -6.827f)
                lineToRelative(85.333f, -55.467f)
                curveToRelative(2.56f, -1.707f, 5.973f, -1.707f, 9.387f, 0f)
                lineToRelative(76.8f, 46.933f)
                curveToRelative(4.267f, 2.56f, 5.12f, 7.68f, 2.56f, 11.947f)
                reflectiveCurveToRelative(-7.68f, 5.12f, -11.947f, 2.56f)
                lineToRelative(-72.533f, -43.52f)
                lineToRelative(-76.8f, 49.493f)
                verticalLineToRelative(89.6f)
                curveToRelative(0f, 5.12f, -3.413f, 8.533f, -8.534f, 8.533f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFCC66A1)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(419.622f, 222.075f)
                curveToRelative(-1.707f, 0f, -3.413f, -0.853f, -4.267f, -1.707f)
                lineToRelative(-85.333f, -55.467f)
                curveToRelative(-2.56f, -1.707f, -4.267f, -4.267f, -4.267f, -6.827f)
                lineTo(325.755f, 64.209f)
                curveToRelative(0f, -5.12f, 3.413f, -8.533f, 8.533f, -8.533f)
                reflectiveCurveToRelative(8.533f, 3.413f, 8.533f, 8.533f)
                verticalLineToRelative(89.6f)
                lineToRelative(76.8f, 49.493f)
                lineToRelative(71.68f, -52.053f)
                curveToRelative(3.413f, -2.56f, 9.387f, -1.707f, 11.947f, 1.707f)
                curveToRelative(2.56f, 3.413f, 1.707f, 9.387f, -1.707f, 11.947f)
                lineToRelative(-76.8f, 55.467f)
                curveToRelative(-1.706f, 1.706f, -3.413f, 1.706f, -5.119f, 1.706f)
                close()
            }

            // Left cube
            path(
                fill = SolidColor(Color(0xFF25B872)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(255.782f, 301.435f)
                lineToRelative(-85.333f, 55.467f)
                lineToRelative(-85.334f, -55.467f)
                verticalLineToRelative(-93.866f)
                lineToRelative(85.334f, -55.467f)
                lineToRelative(85.333f, 55.467f)
                close()
            }

            // Right cube
            path(
                fill = SolidColor(Color(0xFF7D6599)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(255.782f, 207.569f)
                lineToRelative(85.333f, -55.467f)
                lineToRelative(85.334f, 55.467f)
                verticalLineToRelative(93.866f)
                lineToRelative(-85.334f, 55.467f)
                lineToRelative(-85.333f, -55.467f)
                close()
            }

            // Left circle
            path(
                fill = SolidColor(Color(0xFF2CD282)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(213.115f, 254.502f)
                curveToRelative(0f, -23.893f, -18.773f, -42.667f, -42.667f, -42.667f)
                reflectiveCurveToRelative(-42.667f, 18.773f, -42.667f, 42.667f)
                reflectiveCurveToRelative(18.773f, 42.667f, 42.667f, 42.667f)
                reflectiveCurveToRelative(42.667f, -18.774f, 42.667f, -42.667f)
                close()
            }

            // Right circle
            path(
                fill = SolidColor(Color(0xFFCC66A1)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(383.782f, 254.502f)
                curveToRelative(0f, -23.893f, -18.773f, -42.667f, -42.667f, -42.667f)
                reflectiveCurveToRelative(-42.667f, 18.773f, -42.667f, 42.667f)
                reflectiveCurveToRelative(18.773f, 42.667f, 42.667f, 42.667f)
                reflectiveCurveToRelative(42.667f, -18.774f, 42.667f, -42.667f)
                close()
            }

        }.build()

        return _biologyIcon!!
    }

private var _biologyIcon: ImageVector? = null