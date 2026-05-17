package kz.technopark.edoox.feature.presentation.main

import kz.technopark.edoox.shared.core.AppLanguage

interface MainController {

    fun onChangeTheme(isDarkTheme: Boolean)
    fun onChangeLanguageClick()
    fun onContinueClick()
    fun onLanguageSelected(language: AppLanguage)
    fun onDismissLanguageSheet()


}