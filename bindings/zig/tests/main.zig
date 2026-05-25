const testing = @import("std").testing;

const mapvina = @import("mapvina_native");
const support = @import("support.zig");

comptime {
    _ = @import("diagnostics.zig");
    _ = @import("runtime.zig");
    _ = @import("map_lifecycle.zig");
    _ = @import("camera.zig");
    _ = @import("projection.zig");
    _ = @import("map_tuning.zig");
    _ = @import("style_values.zig");
    _ = @import("geojson.zig");
    _ = @import("style_sources.zig");
    _ = @import("resources.zig");
    _ = @import("logging.zig");
    _ = @import("render.zig");
    _ = @import("surface.zig");
}

test "package root hides raw C declarations" {
    try testing.expect(!@hasDecl(mapvina, "c"));
    try testing.expect(!@hasDecl(mapvina, "mln_runtime"));
    try testing.expect(!@hasDecl(mapvina, "mln_runtime_create"));
    try testing.expect(!@hasDecl(mapvina.OwnedJsonValue, "copyFromNative"));
    try testing.expect(!support.typeNameContains(mapvina.RuntimeHandle, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.MapHandle, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.MapProjectionHandle, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.CanonicalTileId, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.CustomGeometrySourceOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.CameraOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.AnimationOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.ProjectionMode, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.ViewportOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.TileOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleTileScheme, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleVectorTileEncoding, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleRasterDemEncoding, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleTileSourceOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.PremultipliedRgba8Image, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleImageOptions, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleImageInfo, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.OwnedStyleImage, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.LocationIndicatorImageKind, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.JsonValue, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.OwnedJsonValue, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.Geometry, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.GeoJson, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.StyleSourceInfo, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.NetworkStatus, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.AmbientCacheOperation, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.OfflineRegionDefinition, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.OwnedOfflineRegion, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.OfflineRegionList, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.LogRecord, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.ResourceRequest, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.ResourceResponse, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.ResourceRequestHandle, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.RenderTargetExtent, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.RenderBackendSupport, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.MetalOwnedTextureDescriptor, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.VulkanOwnedTextureDescriptor, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.FeatureStateSelector, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.RenderedQueryGeometry, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.FeatureQueryResult, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.FeatureExtensionResult, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.RenderSessionHandle, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.MetalOwnedTextureFrameHandle, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.MetalOwnedTextureFrameInfo, "mln_"));
    try testing.expect(!support.typeNameContains(mapvina.RuntimeHandle, "anyopaque"));
    try testing.expect(!support.typeNameContains(mapvina.MapHandle, "anyopaque"));
    try testing.expect(!support.typeNameContains(mapvina.MapProjectionHandle, "anyopaque"));
    try testing.expect(!support.typeNameContains(mapvina.RenderSessionHandle, "anyopaque"));
}

test "package links the native C library" {
    try testing.expectEqual(@as(u32, 0), mapvina.cAbiVersion());
}

test "package validates the supported C ABI version" {
    var diagnostics = mapvina.DiagnosticStore.init(testing.allocator);
    defer diagnostics.deinit();

    try mapvina.validateAbiVersion(&diagnostics);
    try testing.expect(diagnostics.get() == null);
}
