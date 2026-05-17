package kz.technopark.edoox.feature.presentation.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edoox.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme
import kz.technopark.edoox.shared.coreui.theme.language.AppStrings
import kz.technopark.edoox.shared.coreui.view.PrimaryButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun ErrorScreenEntryPoint(
    launcher: ErrorStateScreenLauncher,
) {
    val appTranslations = AppStrings.current
    val viewModel: ErrorStateScreenViewModel = koinViewModel {
        parametersOf(
            launcher,
            appTranslations,
        )
    }
    val uiState by viewModel.uiState.collectAsState()

    ErrorScreen(
        uiState,
        controller = viewModel,
    )
}

@Composable
fun ErrorScreen(
    uiState: ErrorUIState,
    controller: ErrorStateController,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(24.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val composition by rememberLottieComposition {
                val jsonString = Res.readBytes("files/lottie_robot_error.json").decodeToString()
                LottieCompositionSpec.JsonString(jsonString)
            }

            val progress by animateLottieCompositionAsState(
                composition = composition, iterations = Compottie.IterateForever // Loop forever
            )

            Image(
                painter = rememberLottiePainter(
                    composition = composition, progress = { progress }),
                contentDescription = "Lottie Animation",
                modifier = Modifier.size(200.dp),
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = AppStrings.current.oopsSomethingWrong,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = uiState.message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            PrimaryButton(
                onClick = controller::onRepeatClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = AppStrings.current.tryAgain,
            )

            TextButton(
                onClick = controller::onNavigateBackClick,
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(top = 8.dp),
            ) {
                Text(
                    text = AppStrings.current.backToHome, color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() = EdooxTheme(
    darkTheme = true
) {
    ErrorScreen(
        uiState = ErrorUIState(
            message = "Произошла ошибка при загрузке данных",
        ),
        controller = object : ErrorStateController {
            override fun onNavigateBackClick() {}
            override fun onRepeatClick() {}
        },
    )
}