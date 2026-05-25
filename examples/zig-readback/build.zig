const std = @import("std");
const mapvina_build = @import("mapvina_native");

const BuildOptions = struct {
    target: std.Build.ResolvedTarget,
    optimize: std.builtin.OptimizeMode,
    cmake_artifact_dir: std.Build.LazyPath,
    include_dirs: []const std.Build.LazyPath,
    dependency_library_dir: ?std.Build.LazyPath,
    render_backend: mapvina_build.RenderBackend,
};

fn mapvinaNativeModule(b: *std.Build, options: BuildOptions) *std.Build.Module {
    return mapvina_build.mapvinaNativeModule(b, .{
        .target = options.target,
        .optimize = options.optimize,
        .cmake_artifact_dir = options.cmake_artifact_dir,
        .include_dirs = options.include_dirs,
        .render_backend = options.render_backend,
        .dependency_library_dir = options.dependency_library_dir,
    });
}

fn addReadbackExample(b: *std.Build, options: BuildOptions) *std.Build.Step.Compile {
    const example = b.addExecutable(.{
        .name = "zig-readback",
        .root_module = b.createModule(.{
            .root_source_file = b.path("main.zig"),
            .target = options.target,
            .optimize = options.optimize,
        }),
    });

    mapvina_build.addRenderBackendOptions(b, example.root_module, options.render_backend);
    mapvina_build.addIncludePaths(example.root_module, options.include_dirs);
    example.root_module.addImport("mapvina_native", mapvinaNativeModule(b, options));
    mapvina_build.linkRenderBackend(b, example.root_module, .{
        .target = options.target,
        .render_backend = options.render_backend,
        .dependency_library_dir = options.dependency_library_dir,
    });
    b.installArtifact(example);
    return example;
}

pub fn build(b: *std.Build) void {
    const target = b.standardTargetOptions(.{});
    const render_backend = mapvina_build.renderBackend(b);
    const options = BuildOptions{
        .target = target,
        .optimize = b.standardOptimizeOption(.{}),
        .cmake_artifact_dir = mapvina_build.cmakeArtifactDir(b),
        .include_dirs = mapvina_build.includeDirs(b),
        .dependency_library_dir = mapvina_build.dependencyLibraryDir(b),
        .render_backend = render_backend,
    };

    const readback = addReadbackExample(b, options);
    const run_readback = b.addRunArtifact(readback);
    if (b.args) |args| run_readback.addArgs(args);

    const run_step = b.step("run", "Render a map image to map.ppm");
    run_step.dependOn(&run_readback.step);
}
