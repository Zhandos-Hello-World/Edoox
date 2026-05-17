package kz.technopark.edoox.shared.coreui.theme.language

import android.content.Context
import android.os.Build
import android.app.LocaleManager as AndroidLocaleManager
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidLocaleManager(private val context: Context) : LocaleManager {

    private val _currentLocale = MutableStateFlow(getSavedLocale())
    override val currentLocale: StateFlow<String> = _currentLocale.asStateFlow()

    override fun changeLocale(languageCode: String) {
        _currentLocale.value = languageCode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(Context.LOCALE_SERVICE) as AndroidLocaleManager
            localeManager.applicationLocales = LocaleList.forLanguageTags(languageCode)
        } else {
            val appLocale = LocaleListCompat.forLanguageTags(languageCode)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

    private fun getSavedLocale(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(Context.LOCALE_SERVICE) as AndroidLocaleManager
            localeManager.applicationLocales.toLanguageTags().ifEmpty { "ru" }
        } else {
            AppCompatDelegate.getApplicationLocales().toLanguageTags().ifEmpty { "ru" }
        }
    }
}