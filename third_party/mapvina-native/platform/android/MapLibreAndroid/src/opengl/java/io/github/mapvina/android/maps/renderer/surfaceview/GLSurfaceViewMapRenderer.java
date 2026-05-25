package io.github.mapvina.android.maps.renderer.surfaceview;

import android.content.Context;
import androidx.annotation.NonNull;

import io.github.mapvina.android.maps.renderer.egl.EGLConfigChooser;
import io.github.mapvina.android.maps.renderer.egl.EGLContextFactory;
import io.github.mapvina.android.maps.renderer.egl.EGLWindowSurfaceFactory;
import io.github.mapvina.android.maps.renderer.MapRenderer;

public class GLSurfaceViewMapRenderer extends SurfaceViewMapRenderer {

  public GLSurfaceViewMapRenderer(Context context,
                                @NonNull MapLibreGLSurfaceView surfaceView,
                                String localIdeographFontFamily) {
    super(context, surfaceView, localIdeographFontFamily);

    surfaceView.setEGLContextFactory(new EGLContextFactory());
    surfaceView.setEGLWindowSurfaceFactory(new EGLWindowSurfaceFactory());
    surfaceView.setEGLConfigChooser(new EGLConfigChooser());
    surfaceView.setRenderer(this);
    surfaceView.setRenderingRefreshMode(MapRenderer.RenderingRefreshMode.WHEN_DIRTY);
    surfaceView.setPreserveEGLContextOnPause(true);
  }
}
