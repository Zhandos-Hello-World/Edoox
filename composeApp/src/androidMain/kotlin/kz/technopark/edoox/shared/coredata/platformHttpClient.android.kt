package kz.technopark.edoox.shared.coredata

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun platformHttpClient(): HttpClientEngineFactory<*> = OkHttp