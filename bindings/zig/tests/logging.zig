const std = @import("std");
const testing = std.testing;

const mapvina = @import("mapvina_native");

const LogState = struct {
    count: usize = 0,
    saw_parse_style: bool = false,
    saw_message: bool = false,
};

fn recordLog(context: ?*anyopaque, record: mapvina.LogRecord) bool {
    const state: *LogState = @ptrCast(@alignCast(context.?));
    state.count += 1;
    if (std.meta.eql(record.severity, mapvina.LogSeverity.@"error") and
        std.meta.eql(record.event, mapvina.LogEvent.parse_style))
    {
        state.saw_parse_style = true;
    }
    if (record.message.len > 0) state.saw_message = true;
    return true;
}

test "log callback receives and consumes native logs" {
    var state = LogState{};
    try mapvina.setAsyncLogSeverityMask(.none, null);
    try mapvina.setLogCallback(.{ .handler = recordLog, .context = &state }, null);
    defer {
        mapvina.clearLogCallback(null) catch @panic("log callback clear failed");
        mapvina.setAsyncLogSeverityMask(.default, null) catch @panic("log severity restore failed");
    }

    var runtime = try mapvina.RuntimeHandle.init(null);
    defer runtime.close() catch @panic("runtime close failed");

    var map = try mapvina.MapHandle.create(&runtime, .{});
    defer map.close() catch @panic("map close failed");

    try testing.expectError(error.NativeError, map.setStyleJson(testing.allocator, "{"));
    try testing.expect(state.count > 0);
    try testing.expect(state.saw_parse_style);
    try testing.expect(state.saw_message);
}

test "log async severity mask exposes semantic masks" {
    try mapvina.setAsyncLogSeverityMask(.default, null);
    try mapvina.setAsyncLogSeverityMask(.all, null);
    try mapvina.setAsyncLogSeverityMask(.none, null);
    try mapvina.setAsyncLogSeverityMask(.{ .info = true, .@"error" = true }, null);
    try mapvina.setAsyncLogSeverityMask(.default, null);
}

test "log callback can be cleared" {
    var state = LogState{};
    try mapvina.setLogCallback(.{ .handler = recordLog, .context = &state }, null);
    try mapvina.clearLogCallback(null);
}
