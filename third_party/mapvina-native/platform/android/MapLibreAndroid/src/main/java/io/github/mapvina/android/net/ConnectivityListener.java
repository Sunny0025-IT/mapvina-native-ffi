package io.github.mapvina.android.net;

/**
 * Receives updates on connectivity state
 */
public interface ConnectivityListener {

  void onNetworkStateChanged(boolean connected);

}
