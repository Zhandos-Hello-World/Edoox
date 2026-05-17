package kz.technopark.edoox.feature.presentation.loading

import kotlinx.serialization.Serializable

@Serializable
data class LoadingLauncher(
    val titleText: String,
    val loadingBehavior: LoadingBehavior<LoadedData>,
)