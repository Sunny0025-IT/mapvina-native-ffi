function(mln_add_mapvina_native)
  set(MLN_SOURCE_DIR "${PROJECT_SOURCE_DIR}/third_party/mapvina-native")

  if(WIN32)
    add_compile_definitions(NOMINMAX GHC_WIN_DISABLE_WSTRING_STORAGE_TYPE
                            _USE_MATH_DEFINES)
  endif()

  if(NOT EXISTS "${MLN_SOURCE_DIR}/CMakeLists.txt")
    message(
      FATAL_ERROR
        "MapVina Native submodule is missing. Run `mise install` or `git submodule update --init --recursive --depth 1 third_party/mapvina-native`.")
  endif()

  add_subdirectory("${MLN_SOURCE_DIR}" "${PROJECT_BINARY_DIR}/mapvina-native")

  include("${MLN_SOURCE_DIR}/vendor/nunicode.cmake")
  include("${MLN_SOURCE_DIR}/vendor/sqlite.cmake")

  set(MLN_SOURCE_DIR "${MLN_SOURCE_DIR}" PARENT_SCOPE)
endfunction()
