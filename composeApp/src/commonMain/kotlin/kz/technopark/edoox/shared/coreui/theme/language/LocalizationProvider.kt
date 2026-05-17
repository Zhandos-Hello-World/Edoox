package kz.technopark.edoox.shared.coreui.theme.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key

@Composable
fun LocalizationProvider(
    localeManager: LocaleManager,
    content: @Composable () -> Unit
) {
    val currentLanguage by localeManager.currentLocale.collectAsState()

    val strings = allStrings[currentLanguage] ?: RuStrings

    CompositionLocalProvider(
        AppStrings provides strings,
        LocalLocaleManager provides localeManager
    ) {
        key(currentLanguage) {
            content()
        }
    }
}