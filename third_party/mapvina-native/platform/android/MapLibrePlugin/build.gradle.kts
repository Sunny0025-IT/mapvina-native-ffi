repositories {
  google()
  mavenCentral()
}

plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
}

dependencies {
  implementation("com.android.tools.build:gradle:9.1.1")
}

group = "io.github.mapvina"
version = "0.0.1"

gradlePlugin {
  plugins {
    create("cmakePlugin") {
      id = "io.github.mapvina.ccache-plugin"
      implementationClass = "io.github.mapvina.CcachePlugin"
    }
  }
}
