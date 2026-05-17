package kz.technopark.edoox.shared.coreui

import androidx.compose.ui.graphics.Color

fun parseHexToComposeColor(hexColor: String): Color {
    val cleanHex = hexColor.removePrefix("#")

    val argbHex = when (cleanHex.length) {
        6 -> "FF$cleanHex" // No alpha provided? Default to fully opaque (FF)
        8 -> cleanHex
        else -> throw IllegalArgumentException("Invalid hex color format: $hexColor")
    }

    val colorLong = argbHex.toLong(16)
    return Color(colorLong)
}
