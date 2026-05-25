#pragma once

#include <cstdint>

#include "mapvina_native_c.h"

namespace mln::core {

auto network_status_get(std::uint32_t* out_status) -> mln_status;
auto network_status_set(std::uint32_t status) -> mln_status;

}  // namespace mln::core
