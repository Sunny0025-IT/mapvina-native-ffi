package io.github.mapvina.android.location;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import io.github.mapvina.android.R;
import io.github.mapvina.android.utils.BitmapUtils;

class LayerBitmapProvider {

  private final Context context;

  LayerBitmapProvider(Context context) {
    this.context = context;
  }

  Bitmap generateBitmap(@DrawableRes int drawableRes, @ColorInt Integer tintColor) {
    Drawable drawable = BitmapUtils.getDrawableFromRes(context, drawableRes, tintColor);
    return BitmapUtils.getBitmapFromDrawable(drawable);
  }

  Bitmap generateShadowBitmap(@NonNull LocationComponentOptions options) {
    Drawable shadowDrawable = BitmapUtils.getDrawableFromRes(context, R.drawable.maplibre_user_icon_shadow);
    return Utils.generateShadow(shadowDrawable, options.elevation());
  }
}
