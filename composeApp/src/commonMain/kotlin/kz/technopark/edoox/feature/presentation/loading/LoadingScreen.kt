package kz.technopark.edoox.feature.presentation.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
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
import kz.technopark.edoox.shared.coreui.view.NavBar
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LoadingScreenEntryPoint(
    launcher: LoadingLauncher,
) {
    val appTranslations = AppStrings.current
    val viewModel = koinViewModel<LoadingViewModel> {
        parametersOf(
            appTranslations,
            launcher,
        )
    }

    val uiState: LoadingUiState by viewModel.uiState.collectAsState()

    LoadingScreen(
        uiState = uiState,
        controller = viewModel,
    )
}

@Composable
fun LoadingScreen(
    uiState: LoadingUiState,
    controller: LoadingController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavBar(
                title = "",
                showBackButton = true,
                onBackClick = controller::onNavigateBack,
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = {
            LoadingContent(
                uiState = uiState,
                controller = controller,
            )
        },
    )
}

@Composable
fun LoadingContent(
    uiState: LoadingUiState,
    controller: LoadingController,
) {
    var lottieJsonString by remember { mutableStateOf<String?>(null) }
    var currentMessage by remember { mutableStateOf(uiState.initialMessages) }

    LaunchedEffect(Unit) {
        try {
            lottieJsonString = Res.readBytes("files/lottie_robot_loading.json").decodeToString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    if (uiState.dynamicMessages.isNotEmpty()) {
        LaunchedEffect(uiState.dynamicMessages) {
            var index = 0
            while (true) {
                kotlinx.coroutines.delay(2500) // Меняем текст каждые 2.5 секунды
                index = (index + 1) % uiState.dynamicMessages.size
                currentMessage = uiState.dynamicMessages[index]
            }
        }
    }

    LaunchedEffect(controller) {
        controller.startLoading()
    }


    val infiniteTransition = rememberInfiniteTransition(label = "BackgroundPulse")
    val pulseColor by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colorScheme.background,
        targetValue = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f),
        animationSpec = infiniteRepeatable(
            animation = tween(
                3000,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "BgColor"
    )

    Box(
        modifier = Modifier.fillMaxSize().background(
            brush = Brush.verticalGradient(
                colors = listOf(MaterialTheme.colorScheme.background, pulseColor)
            )
        ), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Box(modifier = Modifier.size(280.dp), contentAlignment = Alignment.Center) {
                this@Column.AnimatedVisibility(
                    visible = lottieJsonString != null,
                    enter = fadeIn(animationSpec = tween(500)),
                    exit = fadeOut()
                ) {
                    lottieJsonString?.let { json ->
                        val composition by rememberLottieComposition {
                            LottieCompositionSpec.JsonString(json)
                        }
                        val progress by animateLottieCompositionAsState(
                            composition = composition, iterations = Compottie.IterateForever
                        )
                        Image(
                            painter = rememberLottiePainter(
                                composition = composition, progress = { progress }),
                            contentDescription = "Lottie Animation",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(contentAlignment = Alignment.Center, modifier = Modifier.height(40.dp)) {
                Text(
                    text = currentMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.animateContentSize(),
                )
            }
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() = EdooxTheme {
    LoadingScreen(
        uiState = LoadingUiState(
            initialMessages = "Загрузка материалов...",
            dynamicMessages = listOf(
                "Загрузка материалов...",
                "Откалибруем нейросеть...",
                "Строим кастомный квиз...",
                "Почти готово..."
            ),
        ),
        controller = object : LoadingController {
            override fun onNavigateBack() = Unit
            override fun startLoading() = Unit
        },
    )
}