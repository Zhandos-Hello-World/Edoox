package kz.technopark.edoox.shared.core

import android.util.Log

actual fun log(key: String, value: String) {
    Log.d(key, value)
}