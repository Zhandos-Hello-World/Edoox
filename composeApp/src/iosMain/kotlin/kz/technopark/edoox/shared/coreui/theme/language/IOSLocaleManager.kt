package kz.technopark.edoox.shared.coreui.theme.language

import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue
import platform.Foundation.NSArray
import platform.Foundation.arrayWithObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IOSLocaleManager : LocaleManager {

    private val defaults = NSUserDefaults.standardUserDefaults
    private val _currentLocale = MutableStateFlow(getSavedLocale())
    override val currentLocale: StateFlow<String> = _currentLocale.asStateFlow()

    override fun changeLocale(languageCode: String) {
        _currentLocale.value = languageCode
        val array = NSArray.arrayWithObject(languageCode)
        defaults.setValue(array, forKey = "AppleLanguages")
        defaults.synchronize()
    }

    private fun getSavedLocale(): String {
        val languages = defaults.stringArrayForKey("AppleLanguages")
        val firstLang = languages?.firstOrNull() as? String
        return firstLang?.take(2) ?: "ru"
    }
}