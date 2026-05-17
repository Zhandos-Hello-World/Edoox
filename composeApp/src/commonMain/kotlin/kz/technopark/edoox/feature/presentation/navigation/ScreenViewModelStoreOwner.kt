package kz.technopark.edoox.feature.presentation.navigation

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

class ScreenViewModelStoreOwner : ViewModelStoreOwner, RememberObserver {
    override val viewModelStore: ViewModelStore = ViewModelStore()

    override fun onRemembered() {
    }

    override fun onForgotten() {
        viewModelStore.clear()
    }

    override fun onAbandoned() {
        viewModelStore.clear()
    }
}

@Composable
fun IsolateScreenScope(content: @Composable () -> Unit) {
    val screenOwner = remember { ScreenViewModelStoreOwner() }

    CompositionLocalProvider(
        LocalViewModelStoreOwner provides screenOwner
    ) {
        content()
    }
}