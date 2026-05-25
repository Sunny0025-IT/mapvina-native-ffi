package io.github.mapvina.android.testapp.utils

import io.github.mapvina.android.LibraryLoaderProvider
import io.github.mapvina.android.ModuleProvider
import io.github.mapvina.android.http.HttpRequest
import io.github.mapvina.android.module.loader.LibraryLoaderProviderImpl
import io.github.mapvina.android.testapp.utils.ExampleHttpRequestImpl

/*
 * An example implementation of the ModuleProvider. This is useful primarily for providing
 * a custom implementation of HttpRequest used by the core.
 */
class ExampleCustomModuleProviderImpl : ModuleProvider {
    override fun createHttpRequest(): HttpRequest {
        return ExampleHttpRequestImpl()
    }

    override fun createLibraryLoaderProvider(): LibraryLoaderProvider {
        return LibraryLoaderProviderImpl()
    }
}
