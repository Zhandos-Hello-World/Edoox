package kz.technopark.edoox.di

import org.koin.core.module.Module
import kz.technopark.edoox.shared.coreui.theme.language.IOSLocaleManager
import kz.technopark.edoox.shared.coreui.theme.language.LocaleManager
import org.koin.dsl.module


actual fun platformModule(): Module {
    return module {
        single<LocaleManager> { IOSLocaleManager() }
    }
}