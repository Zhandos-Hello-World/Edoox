package kz.technopark.edoox.shared.coreui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LanguageIcon: ImageVector
    get() {
        if (_languageIcon != null) {
            return _languageIcon!!
        }

        _languageIcon = Builder(
            name = "ChatIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {

            path(
                fill = SolidColor(Color(0xFF4CF5CB)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(258.637f, 8.192f)
                verticalLineToRelative(495.616f)
                curveToRelative(135.658f, -1.405f, 245.193f, -111.823f, 245.193f, -247.808f)
                reflectiveCurveTo(394.295f, 9.597f, 258.637f, 8.192f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFD1FDF2)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(258.637f, 8.192f)
                curveTo(370.231f, 9.902f, 460.256f, 120.2f, 460.256f, 256f)
                reflectiveCurveTo(370.231f, 502.098f, 258.637f, 503.808f)
                curveToRelative(-0.871f, 0.011f, -1.754f, 0.022f, -2.636f, 0.022f)
                curveToRelative(-24.38f, 0f, -47.943f, -3.53f, -70.198f, -10.087f)
                curveToRelative(-22.256f, -6.569f, -43.204f, -16.166f, -62.377f, -28.334f)
                lineTo(8.171f, 503.83f)
                lineToRelative(38.422f, -115.254f)
                curveTo(22.267f, 350.241f, 8.171f, 304.771f, 8.171f, 256f)
                curveTo(8.171f, 119.133f, 119.133f, 8.17f, 256.001f, 8.17f)
                curveToRelative(0.882f, 0f, 1.764f, 0.011f, 2.636f, 0.022f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF68AEF4)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(426.802f, 239.66f)
                lineToRelative(-42.932f, -98.043f)
                curveToRelative(-24.532f, -27.419f, -57.747f, -46.897f, -95.33f, -54.109f)
                curveToRelative(0f, 0f, -0.054f, 336.438f, 0f, 336.972f)
                curveToRelative(79.196f, -15.196f, 139.035f, -84.85f, 139.035f, -168.48f)
                curveToRelative(0f, -5.512f, -0.261f, -10.97f, -0.773f, -16.34f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF7CC8EF)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(359.523f, 141.617f)
                curveToRelative(-18.541f, -25.6f, -43.215f, -44.272f, -71.179f, -52.518f)
                lineToRelative(-64.882f, -1.58f)
                curveToRelative(-23.988f, 4.597f, -46.2f, 14.194f, -65.503f, 27.67f)
                lineTo(86.583f, 228.766f)
                curveToRelative(-1.427f, 8.867f, -2.157f, 17.964f, -2.157f, 27.234f)
                curveToRelative(0f, 8.443f, 0.61f, 16.754f, 1.797f, 24.87f)
                lineToRelative(44.457f, 92.302f)
                curveToRelative(24.26f, 25.949f, 56.483f, 44.348f, 92.781f, 51.32f)
                lineToRelative(64.882f, -1.59f)
                curveToRelative(61.113f, -18.007f, 106.55f, -85.907f, 106.55f, -166.901f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF276AAD)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(383.881f, 141.606f)
                horizontalLineToRelative(-24.358f)
                lineTo(394.894f, 256f)
                horizontalLineToRelative(15.567f)
                curveToRelative(9.031f, 0f, 16.34f, -7.321f, 16.34f, -16.34f)
                curveToRelative(0f, -37.507f, -15.621f, -71.506f, -39.38f, -98.054f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF3891E9)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(394.894f, 256f)
                horizontalLineToRelative(-29.957f)
                verticalLineToRelative(-27.234f)
                horizontalLineToRelative(-108.936f)
                verticalLineToRelative(-32.681f)
                horizontalLineToRelative(32.681f)
                curveToRelative(0f, -9.02f, 7.321f, -16.34f, 16.34f, -16.34f)
                horizontalLineToRelative(10.894f)
                verticalLineToRelative(-38.128f)
                horizontalLineToRelative(43.607f)
                curveToRelative(21.994f, 30.361f, 35.371f, 70.438f, 35.371f, 114.383f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFF3891E9)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(315.915f, 261.447f)
                verticalLineToRelative(103.489f)
                horizontalLineToRelative(-38.128f)
                curveToRelative(-11.983f, 0f, -21.787f, -9.804f, -21.787f, -21.787f)
                verticalLineToRelative(-38.128f)
                horizontalLineToRelative(-10.894f)
                curveToRelative(-12.037f, 0f, -21.787f, -9.75f, -21.787f, -21.787f)
                reflectiveCurveToRelative(9.75f, -21.787f, 21.787f, -21.787f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFF0EBEA)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(288.344f, 422.901f)
                lineToRelative(-30.546f, 4.662f)
                curveToRelative(10.501f, -0.109f, 20.774f, -1.155f, 30.742f, -3.072f)
                curveToRelative(-0.055f, -0.544f, -0.109f, -1.067f, -0.196f, -1.59f)
                close()
            }

            path(
                fill = SolidColor(Color(0xFFFFFFFF)), pathFillType = PathFillType.NonZero
            ) {
                moveTo(288.344f, 89.099f)
                curveToRelative(-2.255f, 15.828f, -15.883f, 28.007f, -32.343f, 28.007f)
                curveToRelative(-17.005f, 0f, -30.981f, -12.996f, -32.539f, -29.587f)
                curveToRelative(10.534f, -2.037f, 21.417f, -3.094f, 32.539f, -3.094f)
                horizontalLineToRelative(1.797f)
                curveToRelative(10.501f, 0.164f, 20.731f, 1.776f, 30.546f, 4.674f)
                close()
            }

        }.build()

        return _languageIcon!!
    }

private var _languageIcon: ImageVector? = null