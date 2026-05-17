package kz.technopark.edoox.shared.coreui.theme.language

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val AppStrings: ProvidableCompositionLocal<AppTranslations> = staticCompositionLocalOf {
    error("Строки (AppTranslations) не инициализированы внутри LocalizationProvider!")
}

val LocalLocaleManager: ProvidableCompositionLocal<LocaleManager> = staticCompositionLocalOf {
    error("LocaleManager не инициализирован внутри LocalizationProvider!")
}