package kz.technopark.edoox.shared.coredata

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun platformHttpClient(): HttpClientEngineFactory<*> = Darwin