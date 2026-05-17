package kz.technopark.edoox.feature.presentation.main

import kz.technopark.edoox.shared.core.AppLanguage

data class MainUIState(
    val isDarkTheme: Boolean,
    val showLanguageBottomSheet: Boolean = false,
    val currentLanguage: AppLanguage = AppLanguage.RU
)