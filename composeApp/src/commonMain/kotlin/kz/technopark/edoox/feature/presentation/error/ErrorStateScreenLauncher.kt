package kz.technopark.edoox.feature.presentation.error

import kotlinx.serialization.Serializable
import kz.technopark.edoox.feature.presentation.loading.LoadedData
import kz.technopark.edoox.feature.presentation.loading.LoadingBehavior
import kz.technopark.edoox.shared.coredata.ErrorType

@Serializable
class ErrorStateScreenLauncher(
    val behavior: LoadingBehavior<LoadedData>,
    val errorType: ErrorType,
)