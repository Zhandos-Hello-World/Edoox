package kz.technopark.edoox.feature.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.Screen
import kz.technopark.edoox.shared.core.AppLanguage
import kz.technopark.edoox.shared.coreui.ThemeManager
import kz.technopark.edoox.shared.coreui.theme.language.LocaleManager

class MainViewModel(
    private val themeManager: ThemeManager,
    private val router: AppRouter,
    private val localeManager: LocaleManager,
) : ViewModel(), MainController {

    private val _uiState = MutableStateFlow(
        MainUIState(
            isDarkTheme = false,
        )
    )
    val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()

    init {
        observeTheme()
        observeLanguage()
    }

    private fun observeLanguage() {
        viewModelScope.launch {
            localeManager.currentLocale.collectLatest { currentLang ->
                _uiState.update {
                    it.copy(
                        currentLanguage = when (currentLang) {
                            "ru" -> AppLanguage.RU
                            "kk" -> AppLanguage.KK
                            else -> AppLanguage.RU
                        }
                    )
                }
            }
        }

    }

    private fun observeTheme() {
        viewModelScope.launch {
            themeManager.isDarkTheme.collectLatest { isDarkTheme ->
                _uiState.update {
                    it.copy(
                        isDarkTheme = isDarkTheme,
                    )
                }
            }
        }
    }

    override fun onChangeTheme(isDarkTheme: Boolean) = themeManager.toggleTheme()

    override fun onContinueClick() {
        router.navigateTo(
            Screen.SubjectSelection,
        )
    }

    override fun onChangeLanguageClick() {
        _uiState.update { it.copy(showLanguageBottomSheet = true) }
    }

    override fun onDismissLanguageSheet() {
        _uiState.update { it.copy(showLanguageBottomSheet = false) }
    }

    override fun onLanguageSelected(language: AppLanguage) {
        _uiState.update {
            it.copy(
                currentLanguage = language,
                showLanguageBottomSheet = false
            )
        }
        localeManager.changeLocale(language.code)
    }
}