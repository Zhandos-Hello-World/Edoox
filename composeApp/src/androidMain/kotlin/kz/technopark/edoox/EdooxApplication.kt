package kz.technopark.edoox

import android.app.Application
import kz.technopark.edoox.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class EdooxApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(applicationContext)
        }
    }
}