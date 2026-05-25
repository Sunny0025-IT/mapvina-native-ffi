package io.github.mapvina.nativeffi.internal.struct;

import static io.github.mapvina.nativeffi.internal.struct.CoreStructs.latLngArray;
import static io.github.mapvina.nativeffi.internal.struct.CoreStructs.latLngBounds;
import static io.github.mapvina.nativeffi.internal.struct.CoreStructs.stringView;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.github.mapvina.nativeffi.geo.CanonicalTileId;
import io.github.mapvina.nativeffi.internal.c.MapVinaNativeC;
import io.github.mapvina.nativeffi.internal.c.mln_canonical_tile_id;
import io.github.mapvina.nativeffi.internal.c.mln_premultiplied_rgba8_image;
import io.github.mapvina.nativeffi.internal.c.mln_string_view;
import io.github.mapvina.nativeffi.internal.c.mln_style_image_info;
import io.github.mapvina.nativeffi.internal.c.mln_style_image_options;
import io.github.mapvina.nativeffi.internal.c.mln_style_source_info;
import io.github.mapvina.nativeffi.internal.c.mln_style_tile_source_options;
import io.github.mapvina.nativeffi.internal.status.Status;
import io.github.mapvina.nativeffi.render.PremultipliedRgba8Image;
import io.github.mapvina.nativeffi.style.SourceInfo;
import io.github.mapvina.nativeffi.style.SourceType;
import io.github.mapvina.nativeffi.style.StyleImageInfo;
import io.github.mapvina.nativeffi.style.StyleImageOptions;
import io.github.mapvina.nativeffi.style.TileSourceOptions;

/** Internal materializers and readers for style structs and short-lived handles. */
public final class StyleStructs {
  private StyleStructs() {}

  public static MemorySegment stringViewArray(List<String> values, Arena arena) {
    var array = mln_string_view.allocateArray(values.size(), arena);
    for (var index = 0; index < values.size(); index++) {
      mln_string_view.asSlice(array, index).copyFrom(stringView(values.get(index), arena));
    }
    return array;
  }

  public static List<String> styleIdList(MemorySegment list) {
    try (var arena = Arena.ofConfined()) {
      var outCount = arena.allocate(ValueLayout.JAVA_LONG);
      Status.check(MapVinaNativeC.mln_style_id_list_count(list, outCount));
      var count = Math.toIntExact(outCount.get(ValueLayout.JAVA_LONG, 0));
      var ids = new ArrayList<String>(count);
      for (var index = 0; index < count; index++) {
        var outId = mln_string_view.allocate(arena);
        Status.check(MapVinaNativeC.mln_style_id_list_get(list, index, outId));
        ids.add(stringView(outId));
      }
      return List.copyOf(ids);
    } finally {
      MapVinaNativeC.mln_style_id_list_destroy(list);
    }
  }

  public static MemorySegment tileSourceOptions(TileSourceOptions options, Arena arena) {
    var segment = MapVinaNativeC.mln_style_tile_source_options_default(arena);
    var fields = 0;
    if (options.hasMinZoom()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_MIN_ZOOM();
      mln_style_tile_source_options.min_zoom(segment, options.minZoom());
    }
    if (options.hasMaxZoom()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_MAX_ZOOM();
      mln_style_tile_source_options.max_zoom(segment, options.maxZoom());
    }
    if (options.hasAttribution()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_ATTRIBUTION();
      mln_style_tile_source_options.attribution(segment, stringView(options.attribution(), arena));
    }
    if (options.hasScheme()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_SCHEME();
      mln_style_tile_source_options.scheme(segment, options.scheme().nativeValue());
    }
    if (options.hasBounds()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_BOUNDS();
      mln_style_tile_source_options.bounds(segment, latLngBounds(options.bounds(), arena));
    }
    if (options.hasTileSize()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_TILE_SIZE();
      mln_style_tile_source_options.tile_size(segment, options.tileSize());
    }
    if (options.hasVectorEncoding()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_VECTOR_ENCODING();
      mln_style_tile_source_options.vector_encoding(
          segment, options.vectorEncoding().nativeValue());
    }
    if (options.hasRasterDemEncoding()) {
      fields |= MapVinaNativeC.MLN_STYLE_TILE_SOURCE_OPTION_RASTER_ENCODING();
      mln_style_tile_source_options.raster_encoding(
          segment, options.rasterDemEncoding().nativeValue());
    }
    mln_style_tile_source_options.fields(segment, fields);
    return segment;
  }

  public static SourceInfo sourceInfo(MemorySegment segment, Optional<String> attribution) {
    var nativeType = mln_style_source_info.type(segment);
    return new SourceInfo(
        SourceType.fromNative(nativeType),
        nativeType,
        mln_style_source_info.is_volatile(segment),
        attribution);
  }

  public static MemorySegment styleImageOptions(StyleImageOptions options, Arena arena) {
    var segment = MapVinaNativeC.mln_style_image_options_default(arena);
    var fields = 0;
    if (options.hasPixelRatio()) {
      fields |= MapVinaNativeC.MLN_STYLE_IMAGE_OPTION_PIXEL_RATIO();
      mln_style_image_options.pixel_ratio(segment, options.pixelRatio());
    }
    if (options.hasSdf()) {
      fields |= MapVinaNativeC.MLN_STYLE_IMAGE_OPTION_SDF();
      mln_style_image_options.sdf(segment, options.sdf());
    }
    mln_style_image_options.fields(segment, fields);
    return segment;
  }

  public static MemorySegment premultipliedRgba8Image(PremultipliedRgba8Image image, Arena arena) {
    var segment = MapVinaNativeC.mln_premultiplied_rgba8_image_default(arena);
    var bytes = image.pixels();
    if (bytes.length > 0) {
      var nativeBytes = arena.allocate(bytes.length);
      MemorySegment.copy(bytes, 0, nativeBytes, ValueLayout.JAVA_BYTE, 0, bytes.length);
      mln_premultiplied_rgba8_image.pixels(segment, nativeBytes);
    }
    mln_premultiplied_rgba8_image.width(segment, image.width());
    mln_premultiplied_rgba8_image.height(segment, image.height());
    mln_premultiplied_rgba8_image.stride(segment, image.stride());
    mln_premultiplied_rgba8_image.byte_length(segment, bytes.length);
    return segment;
  }

  public static StyleImageInfo styleImageInfo(MemorySegment segment) {
    return new StyleImageInfo(
        mln_style_image_info.width(segment),
        mln_style_image_info.height(segment),
        mln_style_image_info.stride(segment),
        mln_style_image_info.byte_length(segment),
        mln_style_image_info.pixel_ratio(segment),
        mln_style_image_info.sdf(segment));
  }

  public static MemorySegment canonicalTileId(CanonicalTileId tileId, Arena arena) {
    var segment = mln_canonical_tile_id.allocate(arena);
    mln_canonical_tile_id.z(segment, tileId.z());
    mln_canonical_tile_id.x(segment, (int) tileId.x());
    mln_canonical_tile_id.y(segment, (int) tileId.y());
    return segment;
  }

  public static MemorySegment imageSourceCoordinates(
      List<io.github.mapvina.nativeffi.geo.LatLng> coordinates, Arena arena) {
    return coordinates.isEmpty() ? MemorySegment.NULL : latLngArray(coordinates, arena);
  }
}
