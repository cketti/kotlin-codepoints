import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.vanniktech.maven.publish)
}

kotlin {
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    js {
        browser()
        nodejs()
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    linuxArm64()
    linuxX64()

    macosX64()
    macosArm64()

    mingwX64()

    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmWasi {
        nodejs()
    }

    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosX64()
    watchosSimulatorArm64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            withJvm()
            group("nonJvm") {
                withJs()
                withNative()
                withWasmJs()
                withWasmWasi()
            }
        }
    }

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

mavenPublishing {
    pom {
        name.set("kotlin-codepoint")
        description.set("Kotlin Multiplatform (KMP) library that adds basic support for Unicode code points.")
    }
}
