import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

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

    js(IR) {
        browser {}
    }

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
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

    @Suppress("OPT_IN_USAGE")
    wasmJs()
    @Suppress("OPT_IN_USAGE")
    wasmWasi()

    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosX64()
    watchosSimulatorArm64()

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val nonJvmMain by creating {
            dependsOn(commonMain.get())
        }
    }

    targets.onEach {
        if (it.platformType != KotlinPlatformType.jvm) {
            it.compilations.getByName("main").source(sourceSets.getByName("nonJvmMain"))
        }
    }
}

@Suppress("UnstableApiUsage")
mavenPublishing {
    pom {
        name.set("kotlin-codepoint")
        description.set("Kotlin Multiplatform (KMP) library that adds basic support for Unicode code points.")
    }
}
