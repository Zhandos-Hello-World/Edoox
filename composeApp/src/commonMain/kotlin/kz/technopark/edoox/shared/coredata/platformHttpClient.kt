package kz.technopark.edoox.shared.coredata

import io.ktor.client.engine.HttpClientEngineFactory

expect fun platformHttpClient(): HttpClientEngineFactory<*>
