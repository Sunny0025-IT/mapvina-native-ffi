package io.github.mapvina.android;

import androidx.annotation.NonNull;

import io.github.mapvina.android.http.HttpRequest;

/**
 * Injects concrete instances of configurable abstractions
 */
public interface ModuleProvider {

  /**
   * Create a new concrete implementation of HttpRequest.
   *
   * @return a new instance of an HttpRequest
   */
  @NonNull
  HttpRequest createHttpRequest();

  /**
   * Get the concrete implementation of LibraryLoaderProvider
   *
   * @return a new instance of LibraryLoaderProvider
   */
  @NonNull
  LibraryLoaderProvider createLibraryLoaderProvider();

}
