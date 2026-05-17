package kz.technopark.edoox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kz.technopark.edoox.feature.presentation.navigation.AppNavigation
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.shared.coreui.ThemeManager
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme
import kz.technopark.edoox.shared.coreui.theme.language.LocaleManager
import kz.technopark.edoox.shared.coreui.theme.language.LocalizationProvider
import org.koin.compose.koinInject

@Composable
fun EdooxApp() {
    val router: AppRouter = koinInject()
    val themeState: ThemeManager = koinInject()
    val localeManager: LocaleManager = koinInject()
    val isDarkTheme by themeState.isDarkTheme.collectAsState()

    LocalizationProvider(localeManager = localeManager) {
        EdooxTheme(
            darkTheme = isDarkTheme,
        ) {
            Column(
                modifier = Modifier.background(
                        MaterialTheme.colorScheme.surface,
                    ).fillMaxSize().systemBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    AppNavigation(
                        router = router,
                    )
                },
            )
        }
    }
}