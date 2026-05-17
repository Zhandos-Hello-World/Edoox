package kz.technopark.edoox.feature.presentation.loading

interface LoadingBehavior<T: LoadedData> {
    suspend fun loadData(): T

    suspend fun onFailLoaded(ex: Exception)

    suspend fun onSuccessLoaded(value: T)
}

interface LoadedData