package kz.technopark.edoox.feature.presentation.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.plus

class DefaultAppRouter: AppRouter {
    private val _navigationStack = MutableStateFlow<List<Screen>>(listOf(Screen.Main))
    override val navigationStack = _navigationStack.asStateFlow()

    override fun navigateTo(screen: Screen) {
        _navigationStack.update { it + screen }
    }

    override fun replace(screen: Screen) {
        if (_navigationStack.value.isEmpty() || _navigationStack.value.last() == screen) {
            return
        }
        _navigationStack.update { it.dropLast(1) + screen }
    }

    override fun pop() {
        _navigationStack.update { if (it.size > 1) it.dropLast(1) else it }
    }

    override fun replaceAll(screen: Screen) {
        _navigationStack.value = listOf(screen)
    }
}