package kz.technopark.edoox.shared.coreui.theme.language

import kotlinx.coroutines.flow.StateFlow

interface LocaleManager {
    val currentLocale: StateFlow<String>
    fun changeLocale(languageCode: String)
}
