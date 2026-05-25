#define MLN_BUILDING_C

#include "diagnostics/diagnostics.hpp"

#include "mapvina_native_c.h"

auto mln_thread_last_error_message(void) noexcept -> const char* {
  return mln::core::thread_last_error_message();
}
