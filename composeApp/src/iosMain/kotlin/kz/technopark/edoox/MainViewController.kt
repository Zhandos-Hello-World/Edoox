package kz.technopark.edoox

import androidx.compose.ui.window.ComposeUIViewController
import kz.technopark.edoox.di.initKoin

fun MainViewController() = ComposeUIViewController { EdooxApp() }

fun InitModule() = initKoin()