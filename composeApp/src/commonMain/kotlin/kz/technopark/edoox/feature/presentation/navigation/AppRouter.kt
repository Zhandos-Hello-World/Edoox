package kz.technopark.edoox.feature.presentation.navigation

import kotlinx.coroutines.flow.StateFlow

interface AppRouter {
    val navigationStack: StateFlow<List<Screen>>
    fun navigateTo(screen: Screen)

    fun replace(screen: Screen)
    fun pop()
    fun replaceAll(screen: Screen)
}