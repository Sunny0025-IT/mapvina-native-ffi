
package io.github.mapvina.android.testapp.camera;

import io.github.mapvina.android.camera.CameraUpdate;
import io.github.mapvina.android.maps.MapLibreMap;

public class CameraMoveTest extends CameraTest {
  @Override
  void executeCameraMovement(CameraUpdate cameraUpdate, MapLibreMap.CancelableCallback callback) {
    maplibreMap.moveCamera(cameraUpdate, callback);
  }
}
