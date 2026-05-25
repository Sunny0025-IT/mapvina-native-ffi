package io.github.mapvina.nativeffi.internal.struct;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import io.github.mapvina.nativeffi.internal.c.MapVinaNativeC;
import io.github.mapvina.nativeffi.internal.c.mln_metal_borrowed_texture_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_metal_context_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_metal_owned_texture_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_metal_owned_texture_frame;
import io.github.mapvina.nativeffi.internal.c.mln_metal_surface_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_render_target_extent;
import io.github.mapvina.nativeffi.internal.c.mln_texture_image_info;
import io.github.mapvina.nativeffi.internal.c.mln_vulkan_borrowed_texture_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_vulkan_context_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_vulkan_owned_texture_descriptor;
import io.github.mapvina.nativeffi.internal.c.mln_vulkan_owned_texture_frame;
import io.github.mapvina.nativeffi.internal.c.mln_vulkan_surface_descriptor;
import io.github.mapvina.nativeffi.render.MetalBorrowedTextureDescriptor;
import io.github.mapvina.nativeffi.render.MetalContextDescriptor;
import io.github.mapvina.nativeffi.render.MetalOwnedTextureDescriptor;
import io.github.mapvina.nativeffi.render.MetalSurfaceDescriptor;
import io.github.mapvina.nativeffi.render.NativePointer;
import io.github.mapvina.nativeffi.render.RenderTargetExtent;
import io.github.mapvina.nativeffi.render.TextureImageInfo;
import io.github.mapvina.nativeffi.render.VulkanBorrowedTextureDescriptor;
import io.github.mapvina.nativeffi.render.VulkanContextDescriptor;
import io.github.mapvina.nativeffi.render.VulkanOwnedTextureDescriptor;
import io.github.mapvina.nativeffi.render.VulkanSurfaceDescriptor;

/** Internal materializers and readers for render target descriptors and frames. */
public final class RenderStructs {
  private RenderStructs() {}

  public static MemorySegment metalOwnedTextureDescriptor(
      MetalOwnedTextureDescriptor descriptor, Arena arena) {
    var segment = MapVinaNativeC.mln_metal_owned_texture_descriptor_default(arena);
    fillExtent(mln_metal_owned_texture_descriptor.extent(segment), descriptor.extent());
    fillMetalContext(mln_metal_owned_texture_descriptor.context(segment), descriptor.context());
    return segment;
  }

  public static MemorySegment metalBorrowedTextureDescriptor(
      MetalBorrowedTextureDescriptor descriptor, Arena arena) {
    var segment = MapVinaNativeC.mln_metal_borrowed_texture_descriptor_default(arena);
    fillExtent(mln_metal_borrowed_texture_descriptor.extent(segment), descriptor.extent());
    mln_metal_borrowed_texture_descriptor.texture(segment, pointer(descriptor.texture()));
    return segment;
  }

  public static MemorySegment vulkanOwnedTextureDescriptor(
      VulkanOwnedTextureDescriptor descriptor, Arena arena) {
    var segment = MapVinaNativeC.mln_vulkan_owned_texture_descriptor_default(arena);
    fillExtent(mln_vulkan_owned_texture_descriptor.extent(segment), descriptor.extent());
    fillVulkanContext(mln_vulkan_owned_texture_descriptor.context(segment), descriptor.context());
    return segment;
  }

  public static MemorySegment vulkanBorrowedTextureDescriptor(
      VulkanBorrowedTextureDescriptor descriptor, Arena arena) {
    var segment = MapVinaNativeC.mln_vulkan_borrowed_texture_descriptor_default(arena);
    fillExtent(mln_vulkan_borrowed_texture_descriptor.extent(segment), descriptor.extent());
    fillVulkanContext(
        mln_vulkan_borrowed_texture_descriptor.context(segment), descriptor.context());
    mln_vulkan_borrowed_texture_descriptor.image(segment, pointer(descriptor.image()));
    mln_vulkan_borrowed_texture_descriptor.image_view(segment, pointer(descriptor.imageView()));
    mln_vulkan_borrowed_texture_descriptor.format(segment, descriptor.format());
    mln_vulkan_borrowed_texture_descriptor.initial_layout(segment, descriptor.initialLayout());
    if (descriptor.hasFinalLayout()) {
      mln_vulkan_borrowed_texture_descriptor.final_layout(segment, descriptor.finalLayout());
    }
    return segment;
  }

  public static MemorySegment metalSurfaceDescriptor(
      MetalSurfaceDescriptor descriptor, Arena arena) {
    var segment = MapVinaNativeC.mln_metal_surface_descriptor_default(arena);
    fillExtent(mln_metal_surface_descriptor.extent(segment), descriptor.extent());
    fillMetalContext(mln_metal_surface_descriptor.context(segment), descriptor.context());
    mln_metal_surface_descriptor.layer(segment, pointer(descriptor.layer()));
    return segment;
  }

  public static MemorySegment vulkanSurfaceDescriptor(
      VulkanSurfaceDescriptor descriptor, Arena arena) {
    var segment = MapVinaNativeC.mln_vulkan_surface_descriptor_default(arena);
    fillExtent(mln_vulkan_surface_descriptor.extent(segment), descriptor.extent());
    fillVulkanContext(mln_vulkan_surface_descriptor.context(segment), descriptor.context());
    mln_vulkan_surface_descriptor.surface(segment, pointer(descriptor.surface()));
    return segment;
  }

  public static TextureImageInfo textureImageInfo(MemorySegment segment) {
    return new TextureImageInfo(
        mln_texture_image_info.width(segment),
        mln_texture_image_info.height(segment),
        mln_texture_image_info.stride(segment),
        mln_texture_image_info.byte_length(segment));
  }

  public static MemorySegment metalOwnedTextureFrame(Arena arena) {
    var segment = mln_metal_owned_texture_frame.allocate(arena);
    mln_metal_owned_texture_frame.size(segment, (int) mln_metal_owned_texture_frame.sizeof());
    return segment;
  }

  public static MemorySegment vulkanOwnedTextureFrame(Arena arena) {
    var segment = mln_vulkan_owned_texture_frame.allocate(arena);
    mln_vulkan_owned_texture_frame.size(segment, (int) mln_vulkan_owned_texture_frame.sizeof());
    return segment;
  }

  private static void fillExtent(MemorySegment segment, RenderTargetExtent extent) {
    mln_render_target_extent.size(segment, (int) mln_render_target_extent.sizeof());
    mln_render_target_extent.width(segment, extent.width());
    mln_render_target_extent.height(segment, extent.height());
    mln_render_target_extent.scale_factor(segment, extent.scaleFactor());
  }

  private static void fillMetalContext(MemorySegment segment, MetalContextDescriptor context) {
    mln_metal_context_descriptor.size(segment, (int) mln_metal_context_descriptor.sizeof());
    mln_metal_context_descriptor.device(segment, pointer(context.device()));
  }

  private static void fillVulkanContext(MemorySegment segment, VulkanContextDescriptor context) {
    mln_vulkan_context_descriptor.size(segment, (int) mln_vulkan_context_descriptor.sizeof());
    mln_vulkan_context_descriptor.instance(segment, pointer(context.instance()));
    mln_vulkan_context_descriptor.physical_device(segment, pointer(context.physicalDevice()));
    mln_vulkan_context_descriptor.device(segment, pointer(context.device()));
    mln_vulkan_context_descriptor.graphics_queue(segment, pointer(context.graphicsQueue()));
    mln_vulkan_context_descriptor.graphics_queue_family_index(
        segment, context.graphicsQueueFamilyIndex());
  }

  private static MemorySegment pointer(NativePointer pointer) {
    return pointer.isNull() ? MemorySegment.NULL : MemorySegment.ofAddress(pointer.address());
  }
}
