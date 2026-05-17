package kz.technopark.edoox.feature.presentation.loading

data class LoadingUiState(
    val initialMessages: String,
    val dynamicMessages: List<String>,
)