package io.github.mapvina.android

import io.github.mapvina.android.testapp.MapLibreApplication

class InstrumentationApplication : MapLibreApplication() {
    fun initializeLeakCanary(): Boolean {
        // do not initialize leak canary during instrumentation tests
        return true
    }
}
