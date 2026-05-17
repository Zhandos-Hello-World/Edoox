package kz.technopark.edoox.shared.coreui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val BrandPurple = Color(0xFF7359FF)
private val LightBg = Color(0xFFF4F5F7)
private val DarkCard = Color(0xFF1E1E24)
private val DarkBg = Color(0xFF121214)

internal val LightColors = lightColorScheme(
    primary = BrandPurple,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFFEDE9FF),
    onPrimaryContainer = BrandPurple,

    secondary = Color(0xFF2196F3),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFE6A92),
    onSecondaryContainer = Color(0xFF0D47A1),

    background = LightBg,
    onBackground = Color(0xFF1A1A1A),

    surface = Color.White,
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFEAEBED),
    onSurfaceVariant = Color(0xFF535456),

    error = Color(0xFFE91E63),
    onError = Color.White,

    onTertiaryContainer = Color(0xFFE6E0E9),
)

internal val DarkColors = darkColorScheme(
    primary = BrandPurple,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF2C2263),
    onPrimaryContainer = Color(0xFFE0D9FF),

    secondary = Color(0xFF64B5F6),
    onSecondary = Color(0xFF0D47A1),
    secondaryContainer = Color(0xFFA72041),
    onSecondaryContainer = Color(0xFFE3F2FD),

    background = DarkBg,
    onBackground = Color(0xFFE3E3E6),

    surface = DarkCard,
    onSurface = Color(0xFFE3E3E6),
    surfaceVariant = Color(0xFF2A2A30),
    onSurfaceVariant = Color(0xFFA1A1AA),

    error = Color(0xFFFF5252),
    onError = Color(0xFF4B0009),

    onTertiaryContainer = Color(0xFF36343B),

)