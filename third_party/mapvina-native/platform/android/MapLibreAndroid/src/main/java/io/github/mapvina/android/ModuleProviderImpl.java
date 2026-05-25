package io.github.mapvina.android;

import androidx.annotation.NonNull;

import io.github.mapvina.android.http.HttpRequest;
import io.github.mapvina.android.module.http.HttpRequestImpl;
import io.github.mapvina.android.module.loader.LibraryLoaderProviderImpl;

public class ModuleProviderImpl implements ModuleProvider {

  @Override
  @NonNull
  public HttpRequest createHttpRequest() {
    return new HttpRequestImpl();
  }

  @NonNull
  @Override
  public LibraryLoaderProvider createLibraryLoaderProvider() {
    return new LibraryLoaderProviderImpl();
  }
}
